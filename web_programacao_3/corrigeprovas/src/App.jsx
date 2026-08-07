import React, { useEffect, useMemo, useState } from "react";
import {
  ArrowLeft,
  BarChart3,
  BookOpen,
  CheckCircle2,
  ChevronRight,
  ClipboardList,
  Eye,
  EyeOff,
  FilePlus2,
  Home,
  LogOut,
  Menu,
  Pencil,
  Plus,
  Search,
  Settings,
  ShieldCheck,
  Trash2,
  UserRound,
  Users,
  X,
  AlertCircle,
  Lock,
  Unlock,
} from "lucide-react";

const STORAGE_KEY = "corrigeprovas-data";
const SESSION_KEY = "corrigeprovas-session";

const mockUsers = [
  {
    id: "prof-1",
    nome: "Mariana Souza",
    email: "professor@corrigeprovas.com",
    senha: "123456",
    perfil: "professor",
  },
  {
    id: "aluno-1",
    nome: "Lucas Oliveira",
    email: "aluno@corrigeprovas.com",
    senha: "123456",
    perfil: "aluno",
  },
];

const initialData = {
  users: mockUsers,
  simulados: [
    {
      id: "sim-1",
      titulo: "Simulado de Matemática - Unidade 1",
      disciplina: "Matemática",
      turma: "8º Ano A",
      dataAplicacao: "2026-08-12",
      descricao: "Revisão dos conteúdos da primeira unidade.",
      professorId: "prof-1",
      resultadosLiberados: false,
      status: "disponivel",
      questoes: [
        { numero: 1, gabarito: "B", assunto: "Frações" },
        { numero: 2, gabarito: "D", assunto: "Porcentagem" },
        { numero: 3, gabarito: "A", assunto: "Equações" },
        { numero: 4, gabarito: "C", assunto: "Geometria" },
        { numero: 5, gabarito: "E", assunto: "Probabilidade" },
      ],
    },
    {
      id: "sim-2",
      titulo: "Avaliação de História do Brasil",
      disciplina: "História",
      turma: "9º Ano B",
      dataAplicacao: "2026-07-28",
      descricao: "Conteúdos sobre República e Era Vargas.",
      professorId: "prof-1",
      resultadosLiberados: true,
      status: "encerrado",
      questoes: [
        { numero: 1, gabarito: "A", assunto: "República Velha" },
        { numero: 2, gabarito: "C", assunto: "Era Vargas" },
        { numero: 3, gabarito: "B", assunto: "Industrialização" },
        { numero: 4, gabarito: "D", assunto: "Estado Novo" },
      ],
    },
    {
      id: "sim-3",
      titulo: "Revisão de Ciências",
      disciplina: "Ciências",
      turma: "7º Ano A",
      dataAplicacao: "2026-08-20",
      descricao: "Simulado ainda sem respostas recebidas.",
      professorId: "prof-1",
      resultadosLiberados: false,
      status: "disponivel",
      questoes: [
        { numero: 1, gabarito: "C", assunto: "Ecossistemas" },
        { numero: 2, gabarito: "A", assunto: "Células" },
      ],
    },
  ],
  tentativas: [
    {
      id: "tent-1",
      alunoId: "aluno-1",
      simuladoId: "sim-1",
      respostas: {
        1: "B",
        2: "A",
        3: "A",
        4: "C",
        5: "D",
      },
      acertos: 3,
      erros: 2,
      percentual: 60,
      nota: 6,
      enviadaEm: "2026-08-14T10:30:00",
    },
    {
      id: "tent-2",
      alunoId: "aluno-1",
      simuladoId: "sim-2",
      respostas: {
        1: "A",
        2: "C",
        3: "B",
        4: "A",
      },
      acertos: 3,
      erros: 1,
      percentual: 75,
      nota: 7.5,
      enviadaEm: "2026-07-29T15:20:00",
    },
  ],
};

function loadData() {
  const stored = localStorage.getItem(STORAGE_KEY);

  if (!stored) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(initialData));
    return initialData;
  }

  return JSON.parse(stored);
}

function formatDate(date) {
  if (!date) return "-";

  return new Intl.DateTimeFormat("pt-BR").format(
    new Date(`${date}T12:00:00`)
  );
}

function formatDateTime(date) {
  if (!date) return "-";

  return new Intl.DateTimeFormat("pt-BR", {
    dateStyle: "short",
    timeStyle: "short",
  }).format(new Date(date));
}

function createId(prefix = "id") {
  return `${prefix}-${Date.now()}-${Math.random().toString(36).slice(2, 7)}`;
}

function App() {
  const [data, setData] = useState(loadData);
  const [session, setSession] = useState(() => {
    const stored = localStorage.getItem(SESSION_KEY);
    return stored ? JSON.parse(stored) : null;
  });

  const [view, setView] = useState("login");
  const [selectedSimulationId, setSelectedSimulationId] = useState(null);
  const [editingSimulationId, setEditingSimulationId] = useState(null);
  const [toast, setToast] = useState(null);

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
  }, [data]);

  useEffect(() => {
    if (session) {
      localStorage.setItem(SESSION_KEY, JSON.stringify(session));

      if (view === "login" || view === "register") {
        setView(
          session.perfil === "professor" ? "prof-dashboard" : "student-dashboard"
        );
      }
    } else {
      localStorage.removeItem(SESSION_KEY);
    }
  }, [session]);

  function notify(message, type = "success") {
    setToast({ message, type });
    setTimeout(() => setToast(null), 3500);
  }

  function navigate(nextView, simulationId = null) {
    setSelectedSimulationId(simulationId);
    setView(nextView);
  }

  function logout() {
    setSession(null);
    setView("login");
    notify("Você saiu da aplicação.");
  }

  function updateData(nextData) {
    setData(nextData);
  }

  if (!session) {
    if (view === "register") {
      return (
        <>
          <RegisterPage
            onBack={() => setView("login")}
            onSuccess={(user) => {
              updateData({
                ...data,
                users: [...data.users, user],
              });
              notify("Conta criada com sucesso. Faça login para continuar.");
              setView("login");
            }}
          />
          <Toast toast={toast} />
        </>
      );
    }

    return (
      <>
        <LoginPage
          onRegister={() => setView("register")}
          onLogin={(user) => {
            setSession(user);
            notify("Login realizado com sucesso.");
          }}
        />
        <Toast toast={toast} />
      </>
    );
  }

  const isProfessor = session.perfil === "professor";

  return (
    <div className="app-shell">
      <Sidebar
        session={session}
        view={view}
        navigate={navigate}
        logout={logout}
      />

      <main className="main-content">
        <Topbar session={session} />

        {isProfessor ? (
          <ProfessorRoutes
            view={view}
            data={data}
            session={session}
            selectedSimulationId={selectedSimulationId}
            editingSimulationId={editingSimulationId}
            navigate={navigate}
            setEditingSimulationId={setEditingSimulationId}
            updateData={updateData}
            notify={notify}
          />
        ) : (
          <StudentRoutes
            view={view}
            data={data}
            session={session}
            selectedSimulationId={selectedSimulationId}
            navigate={navigate}
            updateData={updateData}
            notify={notify}
          />
        )}
      </main>

      <Toast toast={toast} />
    </div>
  );
}

/* =========================
   LOGIN E CADASTRO
========================= */

function LoginPage({ onRegister, onLogin }) {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");

  function submit(event) {
    event.preventDefault();
    setError("");

    if (!email || !senha) {
      setError("Preencha todos os campos.");
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        setError("E-mail inválido.");
        return;
        }

    const users = mockUsers;
    const user = users.find(
      (item) => item.email === email && item.senha === senha
    );

    if (!user) {
      setError("Credenciais inválidas.");
      return;
    }

    onLogin(user);
  }

  return (
    <div className="auth-page">
      <section className="auth-visual">
        <div className="brand brand-light">
          <div className="brand-symbol">
            <CheckCircle2 size={24} />
          </div>
          <span>CorrigeProvas</span>
        </div>

        <div className="visual-content">
          <span className="eyebrow">Correção inteligente</span>
          <h1>Mais tempo para ensinar. Menos tempo corrigindo.</h1>
          <p>
            Cadastre o gabarito, receba as respostas dos alunos e acompanhe os
            resultados de forma simples e segura.
          </p>
        </div>

        <div className="visual-footer">
          <ShieldCheck size={18} />
          Protótipo acadêmico com dados simulados
        </div>
      </section>

      <section className="auth-form-area">
        <div className="auth-card">
          <div className="brand brand-dark">
            <div className="brand-symbol">
              <CheckCircle2 size={24} />
            </div>
            <span>CorrigeProvas</span>
          </div>

          <div className="auth-heading">
            <h2>Bem-vindo de volta</h2>
            <p>Entre para acessar sua área de correção.</p>
          </div>

          <form onSubmit={submit} className="form-stack">
            {error && <Feedback type="error">{error}</Feedback>}

            <Field
              label="E-mail"
              type="email"
              placeholder="voce@email.com"
              value={email}
              onChange={setEmail}
              required
            />

            <div className="field">
              <label htmlFor="login-password">Senha</label>
              <div className="password-wrapper">
                <input
                  id="login-password"
                  type={showPassword ? "text" : "password"}
                  placeholder="Digite sua senha"
                  value={senha}
                  onChange={(event) => setSenha(event.target.value)}
                />
                <button
                  type="button"
                  className="password-toggle"
                  onClick={() => setShowPassword(!showPassword)}
                  aria-label={showPassword ? "Ocultar senha" : "Mostrar senha"}
                >
                  {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                </button>
              </div>
            </div>

            <button className="button button-primary button-full">
              Entrar
            </button>
          </form>

          <div className="demo-box">
            <strong>Acessos para demonstração</strong>
            <span>Professor: professor@corrigeprovas.com / 123456</span>
            <span>Aluno: aluno@corrigeprovas.com / 123456</span>
          </div>

          <p className="auth-switch">
            Ainda não possui uma conta?{" "}
            <button onClick={onRegister}>Criar uma conta</button>
          </p>
        </div>
      </section>
    </div>
  );
}

function RegisterPage({ onBack, onSuccess }) {
  const [form, setForm] = useState({
    nome: "",
    email: "",
    senha: "",
    perfil: "",
  });
  const [error, setError] = useState("");

  function update(field, value) {
    setForm((current) => ({ ...current, [field]: value }));
  }

  function submit(event) {
    event.preventDefault();
    setError("");

    if (!form.nome || !form.email || !form.senha || !form.perfil) {
      setError("Preencha todos os campos obrigatórios.");
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) {
        setError("Informe um e-mail válido.");
        return;
    }

    if (form.senha.length < 6) {
      setError("A senha deve possuir pelo menos 6 caracteres.");
      return;
    }

    onSuccess({
      id: createId("user"),
      ...form,
    });
  }

  return (
    <div className="auth-page auth-page-single">
      <section className="auth-form-area">
        <div className="auth-card register-card">
          <button className="back-link" onClick={onBack}>
            <ArrowLeft size={17} />
            Voltar ao login
          </button>

          <div className="brand brand-dark">
            <div className="brand-symbol">
              <CheckCircle2 size={24} />
            </div>
            <span>CorrigeProvas</span>
          </div>

          <div className="auth-heading">
            <h2>Crie sua conta</h2>
            <p>Escolha seu perfil para começar a utilizar a plataforma.</p>
          </div>

          <form onSubmit={submit} className="form-stack">
            {error && <Feedback type="error">{error}</Feedback>}

            <Field
              label="Nome completo"
              placeholder="Digite seu nome"
              value={form.nome}
              onChange={(value) => update("nome", value)}
              required
            />

            <Field
              label="E-mail"
              type="email"
              placeholder="voce@email.com"
              value={form.email}
              onChange={(value) => update("email", value)}
              required
            />

            <Field
              label="Senha"
              type="password"
              placeholder="Mínimo de 6 caracteres"
              value={form.senha}
              onChange={(value) => update("senha", value)}
              required
            />

            <Select
              label="Tipo de perfil"
              value={form.perfil}
              onChange={(value) => update("perfil", value)}
              options={[
                { value: "", label: "Selecione seu perfil" },
                { value: "professor", label: "Professor" },
                { value: "aluno", label: "Aluno" },
              ]}
              required
            />

            <button className="button button-primary button-full">
              Criar conta
            </button>
          </form>
        </div>
      </section>
    </div>
  );
}

/* =========================
   LAYOUT
========================= */

function Sidebar({ session, view, navigate, logout }) {
  const [open, setOpen] = useState(false);
  const professor = session.perfil === "professor";

  const links = professor
    ? [
        { label: "Dashboard", icon: Home, view: "prof-dashboard" },
        { label: "Meus simulados", icon: ClipboardList, view: "simulations" },
        { label: "Novo simulado", icon: FilePlus2, view: "simulation-form" },
        { label: "Resultados", icon: BarChart3, view: "results" },
      ]
    : [
        { label: "Início", icon: Home, view: "student-dashboard" },
        {
          label: "Simulados disponíveis",
          icon: BookOpen,
          view: "available-simulations",
        },
        { label: "Meus resultados", icon: BarChart3, view: "student-results" },
      ];

  return (
    <>
      <button
        className="mobile-menu-button"
        onClick={() => setOpen(!open)}
        aria-label="Abrir menu"
      >
        <Menu size={21} />
      </button>

      {open && (
        <button
          className="sidebar-overlay"
          onClick={() => setOpen(false)}
          aria-label="Fechar menu"
        />
      )}

      <aside className={`sidebar ${open ? "sidebar-open" : ""}`}>
        <div className="sidebar-brand">
          <div className="brand-symbol">
            <CheckCircle2 size={21} />
          </div>
          <span>CorrigeProvas</span>
        </div>

        <div className="profile-mini">
          <div className="avatar">{session.nome.charAt(0)}</div>
          <div>
            <strong>{session.nome}</strong>
            <span>{professor ? "Professor" : "Aluno"}</span>
          </div>
        </div>

        <nav className="sidebar-nav">
          <span className="nav-label">MENU PRINCIPAL</span>

          {links.map((link) => {
            const Icon = link.icon;

            return (
              <button
                key={link.view}
                className={`nav-link ${view === link.view ? "active" : ""}`}
                onClick={() => {
                  navigate(link.view);
                  setOpen(false);
                }}
              >
                <Icon size={19} />
                {link.label}
              </button>
            );
          })}
        </nav>

        <div className="sidebar-bottom">
          <button className="nav-link">
            <Settings size={19} />
            Configurações
          </button>

          <button className="nav-link logout-link" onClick={logout}>
            <LogOut size={19} />
            Sair
          </button>
        </div>
      </aside>
    </>
  );
}

function Topbar({ session }) {
  return (
    <header className="topbar">
      <div>
        <span className="topbar-context">
          {session.perfil === "professor" ? "Área do professor" : "Área do aluno"}
        </span>
        <span className="topbar-date">
          {new Intl.DateTimeFormat("pt-BR", {
            dateStyle: "full",
          }).format(new Date())}
        </span>
      </div>

      <div className="topbar-user">
        <div className="avatar avatar-small">{session.nome.charAt(0)}</div>
        <span>{session.nome}</span>
      </div>
    </header>
  );
}

/* =========================
   ROTAS DO PROFESSOR
========================= */

function ProfessorRoutes({
  view,
  data,
  session,
  selectedSimulationId,
  editingSimulationId,
  navigate,
  setEditingSimulationId,
  updateData,
  notify,
}) {
  const simulations = data.simulados.filter(
    (item) => item.professorId === session.id
  );

  if (view === "prof-dashboard") {
    return (
      <ProfessorDashboard
        simulations={simulations}
        attempts={data.tentativas}
        navigate={navigate}
      />
    );
  }

  if (view === "simulations") {
    return (
      <SimulationList
        simulations={simulations}
        attempts={data.tentativas}
        navigate={navigate}
        updateData={updateData}
        data={data}
        notify={notify}
      />
    );
  }

  if (view === "simulation-form") {
    const simulation = simulations.find(
      (item) => item.id === editingSimulationId
    );

    return (
      <SimulationForm
        simulation={simulation}
        navigate={navigate}
        data={data}
        updateData={updateData}
        notify={notify}
        session={session}
      />
    );
  }

  if (view === "simulation-detail") {
    const simulation = simulations.find(
      (item) => item.id === selectedSimulationId
    );

    return (
      <SimulationDetail
        simulation={simulation}
        attempts={data.tentativas}
        data={data}
        navigate={navigate}
        updateData={updateData}
        notify={notify}
        setEditingSimulationId={setEditingSimulationId}
      />
    );
  }

  if (view === "results") {
    return (
      <ProfessorResults
        simulations={simulations}
        attempts={data.tentativas}
        data={data}
        updateData={updateData}
        notify={notify}
      />
    );
  }

  return null;
}

function ProfessorDashboard({ simulations, attempts, navigate }) {
  const totalAnswers = attempts.filter((attempt) =>
    simulations.some((simulation) => simulation.id === attempt.simuladoId)
  ).length;

  const blocked = simulations.filter((item) => !item.resultadosLiberados).length;
  const released = simulations.filter((item) => item.resultadosLiberados).length;

  return (
    <Page>
      <PageHeading
        eyebrow="Visão geral"
        title="Olá, Mariana! 👋"
        description="Acompanhe seus simulados e os resultados dos alunos."
        action={
          <button
            className="button button-primary"
            onClick={() => navigate("simulation-form")}
          >
            <Plus size={18} />
            Novo simulado
          </button>
        }
      />

      <div className="stats-grid">
        <StatCard
          icon={<ClipboardList />}
          label="Total de simulados"
          value={simulations.length}
          color="blue"
        />
        <StatCard
          icon={<Lock />}
          label="Resultados bloqueados"
          value={blocked}
          color="yellow"
        />
        <StatCard
          icon={<Unlock />}
          label="Resultados liberados"
          value={released}
          color="green"
        />
        <StatCard
          icon={<Users />}
          label="Respostas recebidas"
          value={totalAnswers}
          color="purple"
        />
      </div>

      <section className="section-card">
        <div className="section-card-header">
          <div>
            <h2>Simulados recentes</h2>
            <p>Visualize rapidamente os últimos simulados cadastrados.</p>
          </div>

          <button
            className="text-button"
            onClick={() => navigate("simulations")}
          >
            Ver todos <ChevronRight size={16} />
          </button>
        </div>

        <SimulationTable
          simulations={simulations.slice(0, 5)}
          attempts={attempts}
          navigate={navigate}
          compact
        />
      </section>
    </Page>
  );
}

function SimulationList({
  simulations,
  attempts,
  navigate,
  updateData,
  data,
  notify,
}) {
  const [search, setSearch] = useState("");
  const [discipline, setDiscipline] = useState("");
  const [classroom, setClassroom] = useState("");
  const [release, setRelease] = useState("");

  const disciplines = [...new Set(simulations.map((item) => item.disciplina))];
  const classrooms = [...new Set(simulations.map((item) => item.turma))];

  const filtered = simulations.filter((item) => {
    const matchesSearch = item.titulo
      .toLowerCase()
      .includes(search.toLowerCase());

    return (
      matchesSearch &&
      (!discipline || item.disciplina === discipline) &&
      (!classroom || item.turma === classroom) &&
      (release === "" ||
        String(item.resultadosLiberados) === release)
    );
  });

  function deleteSimulation(id) {
    const confirmed = window.confirm(
      "Tem certeza de que deseja excluir este simulado? Essa ação não poderá ser desfeita."
    );

    if (!confirmed) return;

    updateData({
      ...data,
      simulados: data.simulados.filter((item) => item.id !== id),
      tentativas: data.tentativas.filter((item) => item.simuladoId !== id),
    });

    notify("Simulado excluído com sucesso.");
  }

  return (
    <Page>
      <PageHeading
        eyebrow="Gestão acadêmica"
        title="Meus simulados"
        description="Crie, acompanhe e gerencie os simulados da sua turma."
        action={
          <button
            className="button button-primary"
            onClick={() => navigate("simulation-form")}
          >
            <Plus size={18} />
            Novo simulado
          </button>
        }
      />

      <section className="filter-card">
        <div className="search-field">
          <Search size={18} />
          <input
            placeholder="Buscar por título..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />
        </div>

        <Select
          compact
          value={discipline}
          onChange={setDiscipline}
          options={[
            { value: "", label: "Todas as disciplinas" },
            ...disciplines.map((item) => ({ value: item, label: item })),
          ]}
        />

        <Select
          compact
          value={classroom}
          onChange={setClassroom}
          options={[
            { value: "", label: "Todas as turmas" },
            ...classrooms.map((item) => ({ value: item, label: item })),
          ]}
        />

        <Select
          compact
          value={release}
          onChange={setRelease}
          options={[
            { value: "", label: "Todos os resultados" },
            { value: "true", label: "Liberados" },
            { value: "false", label: "Bloqueados" },
          ]}
        />
      </section>

      {filtered.length === 0 ? (
        <EmptyState
          icon={<ClipboardList size={30} />}
          title="Nenhum simulado cadastrado ainda."
          description="Crie seu primeiro simulado para começar a receber respostas."
          action={
            <button
              className="button button-primary"
              onClick={() => navigate("simulation-form")}
            >
              Criar simulado
            </button>
          }
        />
      ) : (
        <section className="section-card no-padding">
          <SimulationTable
            simulations={filtered}
            attempts={attempts}
            navigate={navigate}
            onDelete={deleteSimulation}
            onEdit={(id) => navigate("simulation-form", id)}
          />
        </section>
      )}
    </Page>
  );
}

function SimulationTable({
  simulations,
  attempts,
  navigate,
  onDelete,
  onEdit,
  compact = false,
}) {
  return (
    <div className={`table-wrapper ${compact ? "compact-table" : ""}`}>
      <table>
        <thead>
          <tr>
            <th>Simulado</th>
            <th>Disciplina / Turma</th>
            <th>Aplicação</th>
            <th>Questões</th>
            <th>Respostas</th>
            <th>Resultados</th>
            {!compact && <th>Ações</th>}
          </tr>
        </thead>

        <tbody>
          {simulations.map((simulation) => {
            const count = attempts.filter(
              (attempt) => attempt.simuladoId === simulation.id
            ).length;

            return (
              <tr key={simulation.id}>
                <td>
                  <button
                    className="table-title"
                    onClick={() => navigate("simulation-detail", simulation.id)}
                  >
                    {simulation.titulo}
                  </button>
                </td>
                <td>
                  <span>{simulation.disciplina}</span>
                  <small>{simulation.turma}</small>
                </td>
                <td>{formatDate(simulation.dataAplicacao)}</td>
                <td>{simulation.questoes.length}</td>
                <td>{count}</td>
                <td>
                  <StatusBadge
                    released={simulation.resultadosLiberados}
                  />
                </td>

                {!compact && (
                  <td>
                    <div className="table-actions">
                      <button
                        className="icon-button"
                        title="Visualizar"
                        onClick={() =>
                          navigate("simulation-detail", simulation.id)
                        }
                      >
                        <Eye size={17} />
                      </button>
                      <button
                        className="icon-button"
                        title="Editar"
                        onClick={() => onEdit(simulation.id)}
                      >
                        <Pencil size={17} />
                      </button>
                      <button
                        className="icon-button danger"
                        title="Excluir"
                        onClick={() => onDelete(simulation.id)}
                      >
                        <Trash2 size={17} />
                      </button>
                    </div>
                  </td>
                )}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}

function SimulationForm({
  simulation,
  navigate,
  data,
  updateData,
  notify,
  session,
}) {
  const editing = Boolean(simulation);

  const [form, setForm] = useState(
    simulation || {
      titulo: "",
      disciplina: "",
      turma: "",
      dataAplicacao: "",
      descricao: "",
      questoes: [],
    }
  );

  const [question, setQuestion] = useState({
    numero: "",
    gabarito: "",
    assunto: "",
  });

  const [error, setError] = useState("");

  function updateForm(field, value) {
    setForm((current) => ({ ...current, [field]: value }));
  }

  function addQuestion(event) {
    event.preventDefault();
    setError("");

    const number = Number(question.numero);

    if (!Number.isInteger(number) || number <= 0) {
      setError("O número da questão deve ser um inteiro positivo.");
      return;
    }

    if (!["A", "B", "C", "D", "E"].includes(question.gabarito)) {
      setError("O gabarito deve ser A, B, C, D ou E.");
      return;
    }

    if (form.questoes.some((item) => item.numero === number)) {
      setError("Já existe uma questão com esse número.");
      return;
    }

    setForm((current) => ({
      ...current,
      questoes: [...current.questoes, { ...question, numero: number }].sort(
        (a, b) => a.numero - b.numero
      ),
    }));

    setQuestion({ numero: "", gabarito: "", assunto: "" });
    notify("Questão adicionada com sucesso.");
  }

  function removeQuestion(number) {
    setForm((current) => ({
      ...current,
      questoes: current.questoes.filter((item) => item.numero !== number),
    }));
    notify("Questão removida.");
  }

  function submit(event) {
    event.preventDefault();
    setError("");

    if (
      !form.titulo ||
      !form.disciplina ||
      !form.turma ||
      !form.dataAplicacao
    ) {
      setError("Preencha todos os campos obrigatórios.");
      return;
    }

    if (form.questoes.length === 0) {
      setError("O simulado deve possuir pelo menos uma questão.");
      return;
    }

    if (editing) {
      updateData({
        ...data,
        simulados: data.simulados.map((item) =>
          item.id === simulation.id ? { ...item, ...form } : item
        ),
      });

      notify("Simulado atualizado com sucesso.");
      navigate("simulation-detail", simulation.id);
      return;
    }

    const newSimulation = {
      ...form,
      id: createId("sim"),
      professorId: session.id,
      resultadosLiberados: false,
      status: "disponivel",
    };

    updateData({
      ...data,
      simulados: [...data.simulados, newSimulation],
    });

    notify("Simulado criado com sucesso.");
    navigate("simulation-detail", newSimulation.id);
  }

  return (
    <Page>
      <PageHeading
        eyebrow={editing ? "Edição" : "Novo cadastro"}
        title={editing ? "Editar simulado" : "Novo simulado"}
        description="Cadastre somente as informações necessárias para a correção."
        action={
          <button className="button button-secondary" onClick={() => navigate("simulations")}>
            <ArrowLeft size={17} />
            Voltar
          </button>
        }
      />

      <form onSubmit={submit} className="form-page">
        {error && <Feedback type="error">{error}</Feedback>}

        <section className="section-card">
          <div className="section-card-header">
            <div>
              <h2>Dados gerais</h2>
              <p>As informações com asterisco são obrigatórias.</p>
            </div>
          </div>

          <div className="form-grid">
            <Field
              label="Título do simulado"
              required
              value={form.titulo}
              onChange={(value) => updateForm("titulo", value)}
              placeholder="Ex.: Simulado de Matemática - Unidade 1"
            />

            <Field
              label="Disciplina"
              required
              value={form.disciplina}
              onChange={(value) => updateForm("disciplina", value)}
              placeholder="Ex.: Matemática"
            />

            <Field
              label="Turma"
              required
              value={form.turma}
              onChange={(value) => updateForm("turma", value)}
              placeholder="Ex.: 8º Ano A"
            />

            <Field
              label="Data de aplicação"
              required
              type="date"
              value={form.dataAplicacao}
              onChange={(value) => updateForm("dataAplicacao", value)}
            />

            <div className="field field-full">
              <label htmlFor="description">Descrição opcional</label>
              <textarea
                id="description"
                rows="4"
                value={form.descricao}
                onChange={(event) =>
                  updateForm("descricao", event.target.value)
                }
                placeholder="Adicione uma breve descrição para este simulado."
              />
            </div>
          </div>
        </section>

        <section className="section-card">
          <div className="section-card-header">
            <div>
              <h2>Questões e gabarito</h2>
              <p>
                Não cadastre enunciados ou alternativas. Informe apenas o
                número, gabarito e assunto opcional.
              </p>
            </div>
            <span className="question-counter">
              {form.questoes.length} questão(ões)
            </span>
          </div>

          <div className="question-add-grid">
            <Field
              label="Número"
              type="number"
              min="1"
              value={question.numero}
              onChange={(value) =>
                setQuestion((current) => ({ ...current, numero: value }))
              }
              placeholder="1"
            />

            <Select
              label="Gabarito"
              value={question.gabarito}
              onChange={(value) =>
                setQuestion((current) => ({ ...current, gabarito: value }))
              }
              options={[
                { value: "", label: "Selecione" },
                ...["A", "B", "C", "D", "E"].map((letter) => ({
                  value: letter,
                  label: letter,
                })),
              ]}
            />

            <Field
              label="Assunto opcional"
              value={question.assunto}
              onChange={(value) =>
                setQuestion((current) => ({ ...current, assunto: value }))
              }
              placeholder="Ex.: Frações"
            />

            <button className="button button-primary add-question-button" onClick={addQuestion}>
              <Plus size={18} />
              Adicionar questão
            </button>
          </div>

          {form.questoes.length === 0 ? (
            <div className="inline-empty">
              <ClipboardList size={20} />
              Nenhuma questão adicionada ainda.
            </div>
          ) : (
            <div className="question-list">
              {form.questoes.map((item) => (
                <div className="question-row" key={item.numero}>
                  <div className="question-number">{item.numero}</div>
                  <div>
                    <strong>Gabarito: {item.gabarito}</strong>
                    <span>{item.assunto || "Sem assunto informado"}</span>
                  </div>
                  <button
                    type="button"
                    className="icon-button danger"
                    onClick={() => removeQuestion(item.numero)}
                  >
                    <Trash2 size={17} />
                  </button>
                </div>
              ))}
            </div>
          )}
        </section>

        <div className="form-actions">
          <button
            type="button"
            className="button button-secondary"
            onClick={() => navigate("simulations")}
          >
            Cancelar
          </button>
          <button className="button button-primary">
            {editing ? "Salvar alterações" : "Criar simulado"}
          </button>
        </div>
      </form>
    </Page>
  );
}

function SimulationDetail({
  simulation,
  attempts,
  data,
  navigate,
  updateData,
  notify,
  setEditingSimulationId,
}) {
  if (!simulation) {
    return <EmptyState title="Simulado não encontrado." />;
  }

  const simulationAttempts = attempts.filter(
    (attempt) => attempt.simuladoId === simulation.id
  );

  function toggleRelease() {
    const action = simulation.resultadosLiberados ? "bloquear" : "liberar";
    const confirmed = window.confirm(
      `Deseja ${action} os resultados deste simulado?`
    );

    if (!confirmed) return;

    const nextValue = !simulation.resultadosLiberados;

    updateData({
      ...data,
      simulados: data.simulados.map((item) =>
        item.id === simulation.id
          ? { ...item, resultadosLiberados: nextValue }
          : item
      ),
    });

    notify(
      nextValue
        ? "Resultados liberados para os alunos."
        : "Resultados bloqueados."
    );
  }

  return (
    <Page>
      <PageHeading
        eyebrow="Detalhes do simulado"
        title={simulation.titulo}
        description={`${simulation.disciplina} • ${simulation.turma}`}
        action={
          <div className="button-row">
            <button className="button button-secondary" onClick={() => navigate("simulations")}>
              <ArrowLeft size={17} />
              Voltar
            </button>
            <button
              className="button button-primary"
              onClick={() => {
                setEditingSimulationId(simulation.id);
                navigate("simulation-form", simulation.id);
              }}
            >
              <Pencil size={17} />
              Editar simulado
            </button>
          </div>
        }
      />

      <div className="detail-grid">
        <section className="section-card">
          <div className="detail-info-grid">
            <InfoItem label="Disciplina" value={simulation.disciplina} />
            <InfoItem label="Turma" value={simulation.turma} />
            <InfoItem
              label="Data de aplicação"
              value={formatDate(simulation.dataAplicacao)}
            />
            <InfoItem
              label="Total de questões"
              value={simulation.questoes.length}
            />
            <InfoItem
              label="Respostas recebidas"
              value={simulationAttempts.length}
            />
            <InfoItem
              label="Situação dos resultados"
              value={
                <StatusBadge released={simulation.resultadosLiberados} />
              }
            />
          </div>

          {simulation.descricao && (
            <div className="description-block">
              <strong>Descrição</strong>
              <p>{simulation.descricao}</p>
            </div>
          )}
        </section>

        <section className="section-card action-card">
          <h2>Ações rápidas</h2>
          <button
            className="button button-secondary button-full"
            onClick={() => navigate("results")}
          >
            <BarChart3 size={17} />
            Ver resultados
          </button>
          <button
            className={`button ${
              simulation.resultadosLiberados
                ? "button-warning"
                : "button-success"
            } button-full`}
            onClick={toggleRelease}
          >
            {simulation.resultadosLiberados ? (
              <>
                <Lock size={17} />
                Bloquear resultados
              </>
            ) : (
              <>
                <Unlock size={17} />
                Liberar resultados
              </>
            )}
          </button>
        </section>
      </div>

      <section className="section-card">
        <div className="section-card-header">
          <div>
            <h2>Questões cadastradas</h2>
            <p>Lista de números e respectivos gabaritos.</p>
          </div>
        </div>

        <div className="question-list">
          {simulation.questoes.map((item) => (
            <div className="question-row" key={item.numero}>
              <div className="question-number">{item.numero}</div>
              <div>
                <strong>Gabarito: {item.gabarito}</strong>
                <span>{item.assunto || "Sem assunto informado"}</span>
              </div>
              <span className="question-tag">Questão {item.numero}</span>
            </div>
          ))}
        </div>
      </section>
    </Page>
  );
}

function ProfessorResults({ simulations, attempts, data, updateData, notify }) {
  const [search, setSearch] = useState("");
  const [simulationFilter, setSimulationFilter] = useState("");
  const [statusFilter, setStatusFilter] = useState("");

  const results = attempts
    .filter((attempt) =>
      simulations.some((simulation) => simulation.id === attempt.simuladoId)
    )
    .map((attempt) => ({
      ...attempt,
      aluno: data.users.find((user) => user.id === attempt.alunoId),
      simulado: simulations.find(
        (simulation) => simulation.id === attempt.simuladoId
      ),
    }))
    .filter((item) => {
      const matchesName = item.aluno?.nome
        .toLowerCase()
        .includes(search.toLowerCase());

      const released = item.simulado?.resultadosLiberados;

      return (
        matchesName &&
        (!simulationFilter || item.simuladoId === simulationFilter) &&
        (!statusFilter ||
          (statusFilter === "liberado" && released) ||
          (statusFilter === "bloqueado" && !released))
      );
    });

  function setRelease(simulatedId, value) {
    updateData({
      ...data,
      simulados: data.simulados.map((item) =>
        item.id === simulatedId
          ? { ...item, resultadosLiberados: value }
          : item
      ),
    });

    notify(value ? "Resultados liberados para os alunos." : "Resultados bloqueados.");
  }

  return (
    <Page>
      <PageHeading
        eyebrow="Acompanhamento"
        title="Resultados dos alunos"
        description="Acompanhe o desempenho e gerencie a visualização das notas."
      />

      <section className="filter-card">
        <div className="search-field">
          <Search size={18} />
          <input
            placeholder="Buscar por nome do aluno..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />
        </div>

        <Select
          compact
          value={simulationFilter}
          onChange={setSimulationFilter}
          options={[
            { value: "", label: "Todos os simulados" },
            ...simulations.map((item) => ({
              value: item.id,
              label: item.titulo,
            })),
          ]}
        />

        <Select
          compact
          value={statusFilter}
          onChange={setStatusFilter}
          options={[
            { value: "", label: "Todas as situações" },
            { value: "liberado", label: "Resultados liberados" },
            { value: "bloqueado", label: "Resultados bloqueados" },
          ]}
        />
      </section>

      {results.length === 0 ? (
        <EmptyState
          icon={<Users size={30} />}
          title="Nenhum aluno respondeu a este simulado ainda."
          description="Os resultados aparecerão aqui após o envio das respostas."
        />
      ) : (
        <section className="section-card no-padding">
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>Aluno</th>
                  <th>Simulado</th>
                  <th>Acertos</th>
                  <th>Erros</th>
                  <th>Nota</th>
                  <th>Percentual</th>
                  <th>Envio</th>
                  <th>Situação</th>
                  <th>Ação</th>
                </tr>
              </thead>
              <tbody>
                {results.map((result) => (
                  <tr key={result.id}>
                    <td>
                      <strong>{result.aluno?.nome}</strong>
                      <small>{result.aluno?.email}</small>
                    </td>
                    <td>{result.simulado?.titulo}</td>
                    <td>{result.acertos}</td>
                    <td>{result.erros}</td>
                    <td>
                      <strong>{result.nota.toFixed(1)}</strong>
                    </td>
                    <td>{result.percentual}%</td>
                    <td>{formatDateTime(result.enviadaEm)}</td>
                    <td>
                      <StatusBadge released={result.simulado?.resultadosLiberados} />
                    </td>
                    <td>
                      <button
                        className="small-button"
                        onClick={() =>
                          setRelease(
                            result.simuladoId,
                            !result.simulado.resultadosLiberados
                          )
                        }
                      >
                        {result.simulado.resultadosLiberados
                          ? "Bloquear"
                          : "Liberar"}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </section>
      )}
    </Page>
  );
}

/* =========================
   ROTAS DO ALUNO
========================= */

function StudentRoutes({
  view,
  data,
  session,
  selectedSimulationId,
  navigate,
  updateData,
  notify,
}) {
  const simulations = data.simulados;
  const attempts = data.tentativas.filter(
    (attempt) => attempt.alunoId === session.id
  );

  if (view === "student-dashboard") {
    return (
      <StudentDashboard
        simulations={simulations}
        attempts={attempts}
        navigate={navigate}
      />
    );
  }

  if (view === "available-simulations") {
    return (
      <AvailableSimulations
        simulations={simulations}
        attempts={attempts}
        navigate={navigate}
      />
    );
  }

  if (view === "answer-simulation") {
    const simulation = simulations.find(
      (item) => item.id === selectedSimulationId
    );

    return (
      <AnswerSimulation
        simulation={simulation}
        attempt={attempts.find(
          (item) => item.simuladoId === selectedSimulationId
        )}
        session={session}
        data={data}
        updateData={updateData}
        navigate={navigate}
        notify={notify}
      />
    );
  }

  if (view === "student-results") {
    return (
      <StudentResults
        simulations={simulations}
        attempts={attempts}
      />
    );
  }

  return null;
}

function StudentDashboard({ simulations, attempts, navigate }) {
  const answeredIds = attempts.map((item) => item.simuladoId);
  const released = attempts.filter((attempt) => {
    const simulation = simulations.find(
      (item) => item.id === attempt.simuladoId
    );

    return simulation?.resultadosLiberados;
  }).length;

  const available = simulations.filter(
    (simulation) => !answeredIds.includes(simulation.id)
  ).length;

  return (
    <Page>
      <PageHeading
        eyebrow="Visão geral"
        title="Olá, Lucas! 👋"
        description="Confira seus simulados e acompanhe seu desempenho."
        action={
          <button
            className="button button-primary"
            onClick={() => navigate("available-simulations")}
          >
            <BookOpen size={18} />
            Ver simulados
          </button>
        }
      />

      <div className="stats-grid">
        <StatCard
          icon={<BookOpen />}
          label="Simulados disponíveis"
          value={available}
          color="blue"
        />
        <StatCard
          icon={<CheckCircle2 />}
          label="Simulados respondidos"
          value={attempts.length}
          color="green"
        />
        <StatCard
          icon={<BarChart3 />}
          label="Resultados liberados"
          value={released}
          color="purple"
        />
        <StatCard
          icon={<Lock />}
          label="Aguardando liberação"
          value={attempts.length - released}
          color="yellow"
        />
      </div>

      <section className="section-card student-welcome">
        <div className="welcome-icon">
          <ClipboardList size={30} />
        </div>
        <div>
          <h2>Pronto para continuar?</h2>
          <p>
            Acesse os simulados disponíveis, informe as respostas que você
            marcou fora da plataforma e envie sua tentativa.
          </p>
          <button
            className="text-button"
            onClick={() => navigate("available-simulations")}
          >
            Acessar simulados disponíveis <ChevronRight size={16} />
          </button>
        </div>
      </section>
    </Page>
  );
}

function AvailableSimulations({ simulations, attempts, navigate }) {
  const [search, setSearch] = useState("");
  const [discipline, setDiscipline] = useState("");
  const [classroom, setClassroom] = useState("");

  const disciplines = [...new Set(simulations.map((item) => item.disciplina))];
  const classrooms = [...new Set(simulations.map((item) => item.turma))];

  const filtered = simulations.filter((simulation) => {
    const attempt = attempts.find(
      (item) => item.simuladoId === simulation.id
    );

    return (
      simulation.titulo.toLowerCase().includes(search.toLowerCase()) &&
      (!discipline || simulation.disciplina === discipline) &&
      (!classroom || simulation.turma === classroom)
    );
  });

  function getStatus(simulation) {
    const attempt = attempts.find(
      (item) => item.simuladoId === simulation.id
    );

    if (!attempt) return "Ainda não respondido";
    if (simulation.resultadosLiberados) return "Resultado disponível";
    return "Resultado aguardando liberação";
  }

  return (
    <Page>
      <PageHeading
        eyebrow="Área do aluno"
        title="Simulados disponíveis"
        description="Responda aos simulados aplicados pelo seu professor."
      />

      <section className="filter-card">
        <div className="search-field">
          <Search size={18} />
          <input
            placeholder="Buscar por título..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />
        </div>

        <Select
          compact
          value={discipline}
          onChange={setDiscipline}
          options={[
            { value: "", label: "Todas as disciplinas" },
            ...disciplines.map((item) => ({ value: item, label: item })),
          ]}
        />

        <Select
          compact
          value={classroom}
          onChange={setClassroom}
          options={[
            { value: "", label: "Todas as turmas" },
            ...classrooms.map((item) => ({ value: item, label: item })),
          ]}
        />
      </section>

      <div className="simulation-card-grid">
        {filtered.map((simulation) => {
          const attempt = attempts.find(
            (item) => item.simuladoId === simulation.id
          );
          const status = getStatus(simulation);

          return (
            <article className="simulation-card" key={simulation.id}>
              <div className="simulation-card-top">
                <span className="discipline-icon">
                  <BookOpen size={20} />
                </span>
                <StatusText status={status} />
              </div>

              <h2>{simulation.titulo}</h2>
              <p className="simulation-description">
                {simulation.descricao || "Sem descrição cadastrada."}
              </p>

              <div className="simulation-meta">
                <span>
                  <BookOpen size={15} />
                  {simulation.disciplina}
                </span>
                <span>
                  <Users size={15} />
                  {simulation.turma}
                </span>
                <span>
                  <ClipboardList size={15} />
                  {simulation.questoes.length} questões
                </span>
                <span>Aplicação: {formatDate(simulation.dataAplicacao)}</span>
              </div>

              {attempt ? (
                <button
                  className="button button-secondary button-full"
                  onClick={() => navigate("student-results")}
                >
                  Ver status
                </button>
              ) : (
                <button
                  className="button button-primary button-full"
                  onClick={() => navigate("answer-simulation", simulation.id)}
                >
                  Responder simulado
                  <ChevronRight size={17} />
                </button>
              )}
            </article>
          );
        })}
      </div>
    </Page>
  );
}

function AnswerSimulation({
  simulation,
  attempt,
  session,
  data,
  updateData,
  navigate,
  notify,
}) {
  const [answers, setAnswers] = useState({});
  const [error, setError] = useState("");

  if (!simulation) {
    return <EmptyState title="Simulado não encontrado." />;
  }

  if (attempt) {
    return (
      <Page>
        <EmptyState
          icon={<CheckCircle2 size={30} />}
          title="Você já respondeu este simulado."
          description="Sua tentativa foi registrada e não pode ser alterada."
          action={
            <button
              className="button button-primary"
              onClick={() => navigate("student-results")}
            >
              Ver meus resultados
            </button>
          }
        />
      </Page>
    );
  }

  function chooseAnswer(number, value) {
    setAnswers((current) => ({
      ...current,
      [number]: value,
    }));
  }

  function submit(event) {
    event.preventDefault();
    setError("");

    const unanswered = simulation.questoes.filter(
      (question) => !answers[question.numero]
    );

    if (unanswered.length > 0) {
      setError(
        `Responda todas as questões antes de enviar. Faltam ${unanswered.length}.`
      );
      return;
    }

    const confirmed = window.confirm(
      "Após o envio, você não poderá alterar suas respostas nem realizar uma nova tentativa. Deseja continuar?"
    );

    if (!confirmed) return;

    const correct = simulation.questoes.filter(
      (question) => answers[question.numero] === question.gabarito
    ).length;

    const total = simulation.questoes.length;
    const errors = total - correct;
    const percentage = Math.round((correct / total) * 100);
    const grade = Number(((correct / total) * 10).toFixed(1));

    const newAttempt = {
      id: createId("attempt"),
      alunoId: session.id,
      simuladoId: simulation.id,
      respostas: answers,
      acertos: correct,
      erros: errors,
      percentual: percentage,
      nota: grade,
      enviadaEm: new Date().toISOString(),
    };

    updateData({
      ...data,
      tentativas: [...data.tentativas, newAttempt],
    });

    notify("Respostas enviadas com sucesso.");
    navigate("student-results");
  }

  return (
    <Page>
      <PageHeading
        eyebrow="Resposta do simulado"
        title={simulation.titulo}
        description={`${simulation.disciplina} • ${simulation.turma}`}
        action={
          <button
            className="button button-secondary"
            onClick={() => navigate("available-simulations")}
          >
            <ArrowLeft size={17} />
            Voltar
          </button>
        }
      />

      <div className="instruction-box">
        <AlertCircle size={21} />
        <div>
          <strong>Instruções importantes</strong>
          <p>
            Informe apenas as letras que você marcou na prova aplicada fora da
            plataforma. Todas as questões são obrigatórias. O envio será
            definitivo.
          </p>
        </div>
      </div>

      <form onSubmit={submit} className="answer-form">
        {error && <Feedback type="error">{error}</Feedback>}

        <section className="section-card">
          <div className="answer-list">
            {simulation.questoes.map((question) => (
              <div
                className={`answer-question ${
                  error && !answers[question.numero] ? "unanswered" : ""
                }`}
                key={question.numero}
              >
                <div className="answer-question-title">
                  <span>Questão {question.numero}</span>
                  {question.assunto && <small>{question.assunto}</small>}
                </div>

                <div className="answer-options">
                  {["A", "B", "C", "D", "E"].map((letter) => (
                    <label
                      className={`answer-option ${
                        answers[question.numero] === letter ? "selected" : ""
                      }`}
                      key={letter}
                    >
                      <input
                        type="radio"
                        name={`question-${question.numero}`}
                        checked={answers[question.numero] === letter}
                        onChange={() =>
                          chooseAnswer(question.numero, letter)
                        }
                      />
                      <span>{letter}</span>
                    </label>
                  ))}
                </div>
              </div>
            ))}
          </div>
        </section>

        <div className="form-actions">
          <button
            type="button"
            className="button button-secondary"
            onClick={() => navigate("available-simulations")}
          >
            Cancelar
          </button>
          <button className="button button-primary">
            <CheckCircle2 size={18} />
            Enviar respostas
          </button>
        </div>
      </form>
    </Page>
  );
}

function StudentResults({ simulations, attempts }) {
  const results = attempts.map((attempt) => ({
    ...attempt,
    simulation: simulations.find(
      (simulation) => simulation.id === attempt.simuladoId
    ),
  }));

  return (
    <Page>
      <PageHeading
        eyebrow="Desempenho"
        title="Meus resultados"
        description="Consulte suas tentativas e notas após a liberação pelo professor."
      />

      {results.length === 0 ? (
        <EmptyState
          icon={<BarChart3 size={30} />}
          title="Você ainda não possui resultados."
          description="Responda a um simulado disponível para acompanhar seu desempenho."
        />
      ) : (
        <div className="results-grid">
          {results.map((result) => {
            const released = result.simulation?.resultadosLiberados;

            return (
              <article className="result-card" key={result.id}>
                <div className="result-card-header">
                  <div>
                    <span className="result-discipline">
                      {result.simulation?.disciplina}
                    </span>
                    <h2>{result.simulation?.titulo}</h2>
                  </div>
                  <StatusBadge released={released} />
                </div>

                <p className="result-date">
                  Enviado em {formatDateTime(result.enviadaEm)}
                </p>

                {released ? (
                  <>
                    <div className="grade-display">
                      <strong>{result.nota.toFixed(1)}</strong>
                      <span>nota</span>
                    </div>

                    <div className="result-metrics">
                      <Metric label="Acertos" value={result.acertos} />
                      <Metric label="Erros" value={result.erros} />
                      <Metric
                        label="Aproveitamento"
                        value={`${result.percentual}%`}
                      />
                    </div>

                    <div className="performance-bar">
                      <span style={{ width: `${result.percentual}%` }} />
                    </div>
                    <small className="performance-label">
                      Aproveitamento de {result.percentual}%
                    </small>
                  </>
                ) : (
                  <div className="locked-result">
                    <Lock size={27} />
                    <strong>Aguardando liberação</strong>
                    <p>
                      O professor ainda não liberou a visualização deste
                      resultado.
                    </p>
                  </div>
                )}
              </article>
            );
          })}
        </div>
      )}
    </Page>
  );
}

/* =========================
   COMPONENTES REUTILIZÁVEIS
========================= */

function Page({ children }) {
  return <div className="page">{children}</div>;
}

function PageHeading({ eyebrow, title, description, action }) {
  return (
    <div className="page-heading">
      <div>
        {eyebrow && <span className="eyebrow dark">{eyebrow}</span>}
        <h1>{title}</h1>
        {description && <p>{description}</p>}
      </div>
      {action && <div className="page-heading-action">{action}</div>}
    </div>
  );
}

function Field({
  label,
  value,
  onChange,
  type = "text",
  placeholder,
  required = false,
  min,
}) {
  const id = `field-${label.toLowerCase().replace(/\s/g, "-")}`;

  return (
    <div className="field">
      <label htmlFor={id}>
        {label} {required && <span>*</span>}
      </label>
      <input
        id={id}
        type={type}
        min={min}
        value={value}
        placeholder={placeholder}
        onChange={(event) => onChange(event.target.value)}
        required={required}
      />
    </div>
  );
}

function Select({
  label,
  value,
  onChange,
  options,
  required = false,
  compact = false,
}) {
  const id = `select-${label || "field"}`;

  return (
    <div className={`field ${compact ? "compact-field" : ""}`}>
      {label && (
        <label htmlFor={id}>
          {label} {required && <span>*</span>}
        </label>
      )}
      <select
        id={id}
        value={value}
        onChange={(event) => onChange(event.target.value)}
        required={required}
      >
        {options.map((option) => (
          <option value={option.value} key={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
}

function StatCard({ icon, label, value, color }) {
  return (
    <div className="stat-card">
      <div className={`stat-icon ${color}`}>{icon}</div>
      <div>
        <span>{label}</span>
        <strong>{value}</strong>
      </div>
    </div>
  );
}

function StatusBadge({ released }) {
  return (
    <span className={`status-badge ${released ? "status-success" : "status-warning"}`}>
      {released ? <Unlock size={14} /> : <Lock size={14} />}
      {released ? "Liberado" : "Bloqueado"}
    </span>
  );
}

function StatusText({ status }) {
  const available = status === "Ainda não respondido";
  const released = status === "Resultado disponível";

  return (
    <span
      className={`status-text ${
        available ? "available" : released ? "released" : "waiting"
      }`}
    >
      {available ? "Disponível" : status}
    </span>
  );
}

function Feedback({ type = "success", children }) {
  return (
    <div className={`feedback feedback-${type}`}>
      {type === "success" ? (
        <CheckCircle2 size={18} />
      ) : (
        <AlertCircle size={18} />
      )}
      <span>{children}</span>
    </div>
  );
}

function EmptyState({ icon, title, description, action }) {
  return (
    <section className="empty-state">
      <div className="empty-icon">{icon || <ClipboardList size={30} />}</div>
      <h2>{title || "Nenhum registro encontrado."}</h2>
      {description && <p>{description}</p>}
      {action && action}
    </section>
  );
}

function InfoItem({ label, value }) {
  return (
    <div className="info-item">
      <span>{label}</span>
      <strong>{value}</strong>
    </div>
  );
}

function Metric({ label, value }) {
  return (
    <div className="metric">
      <span>{label}</span>
      <strong>{value}</strong>
    </div>
  );
}

function Toast({ toast }) {
  if (!toast) return null;

  return (
    <div className={`toast toast-${toast.type}`}>
      {toast.type === "error" ? (
        <AlertCircle size={18} />
      ) : (
        <CheckCircle2 size={18} />
      )}
      {toast.message}
    </div>
  );
}

export default App;