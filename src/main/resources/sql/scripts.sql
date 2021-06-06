-- Tabelas base --

create table PerfilAcesso (
 id numeric(5) primary key,
 role varchar(20) not null,
 constraint perfilAcesso_uk unique (role)
);

create table Login (
 id numeric(5) primary key,
 login varchar(15) not null,
 senha varchar(15) not null,
 constraint login_uk unique (login)
);

create table Usuario (
 id numeric(5) primary key,
 nome varchar(35) not null,
 email varchar(25) not null,
 login numeric(5) not null,
 constraint log_usu_fk foreign key (login) references Login(id)
);

create table Endereco (
 id numeric(5) primary key,
 logradouro varchar(70),
 numero varchar(20) not null,
 complemento varchar(25),
 bairro varchar(25),
 cidade varchar(25),
 uf varchar(2),
 pais varchar(20),
 cep numeric(8) not null,
 usuario numeric(5) not null,
 constraint usu_end_fk foreign key (usuario) references Usuario(id)
);

create table Telefone (
 id numeric(5) primary key,
 ddd numeric(3) not null,
 numero numeric(8) not null,
 usuario numeric(5) not null,
 constraint usu_tel_fk foreign key (usuario) references Usuario(id)
);

create table Administrador (
 id numeric(5) primary key,
 usuario numeric(5) not null,
 constraint usu_adm_fk foreign key (usuario) references Usuario(id)
);

create table Nutricionista (
 id numeric(5) primary key,
 crn varchar(15) not null,
 aprovado numeric(1) not null,
 usuario numeric(5) not null,
 constraint crn_uk unique (crn),
 constraint usu_nut_fk foreign key (usuario) references Usuario(id)
);

create table PerfilPaciente (
 id numeric(5) primary key,
 perfil varchar(15) not null,
 constraint perfilPaciente_uk unique (perfil)
);

create table Paciente (
 id numeric(5) primary key,
 usuario numeric(5) not null,
 dataNascto date not null,
 peso numeric(4,2) not null,
 altura numeric(3,2) not null,
 sexo numeric(1) not null,
 perfil numeric(5) not null,
 constraint usu_pac_fk foreign key (usuario) references Usuario(id),
 constraint per_pac_fk foreign key (perfil) references PerfilPaciente(id)
);

create table FrequenciaAtividade (
 id numeric(5) primary key,
 frequenciaDiaria varchar(15) not null,
 constraint frequenciaatividade_uk unique (frequenciadiaria)
);

create table Atividade (
 id numeric(5) primary key,
 nome varchar(15) not null,
 constraint atividade_uk unique (nome)
);

create table Patologia (
 id numeric(5) primary key,
 nome varchar(20) not null,
 descricao varchar(100) not null,
 constraint patologia_uk unique (nome)
);

create table GrupoAlimentar (
 id numeric(5) primary key,
 nome varchar(40) not null,
 constraint grupo_uk unique (nome)
);

create table Categoria (
 id numeric(5) primary key,
 nome varchar(20) not null,
 grupo numeric(5) not null,
 constraint gru_cat_fk foreign key (grupo) references GrupoAlimentar(id)
);

create table Medida (
 id numeric(5) primary key,
 nome varchar(15) not null,
 quantidade numeric(5,2) not null,
 constraint medida_uk unique (nome)
);

create table Alimento (
 id numeric(5) primary key,
 nome varchar(50) not null,
 categoria numeric(5) not null,
 medida numeric(5) not null,
 constraint cat_ali_fk foreign key (categoria) references Categoria(id),
 constraint med_ali_fk foreign key (medida) references Medida(id)
);

create table Nutriente (
 id numeric(5) primary key,
 nome varchar(15) not null,
 constraint nutriente_uk unique (nome)
);

create table HistoricoAlimentar (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 alimento numeric(5) not null,
 quantidade numeric(4,2) not null,
 data date not null,
 hora time not null,
 constraint pac_his_fk foreign key (paciente) references Paciente(id),
 constraint ali_his_fk foreign key (alimento) references Alimento(id)
);

create table Cardapio (
 id numeric(5) primary key
);

create table ResultadoAnalise (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 dataInicial date not null,
 dataFinal date not null,
 posicaoRanking numeric(5) not null,
 informativo varchar(40),
 constraint pac_res_fk foreign key (paciente) references Paciente(id)
);


-- Tabelas de relacionamento --

create table Login_PerfilAcesso (
 id numeric(5) primary key,
 login numeric(5) not null,
 role numeric(5) not null,
 constraint log_per_fk foreign key (login) references Login(id),
 constraint per_log_fk foreign key (role) references PerfilAcesso(id)
);

create table Paciente_Nutricionista (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 nutricionista numeric(5) not null,
 constraint pac_nut_fk foreign key (paciente) references Paciente(id),
 constraint nut_pac_fk foreign key (nutricionista) references Nutricionista(id)
);

create table Paciente_Cardapio (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 cardapio numeric(5) not null,
 constraint pac_car_fk foreign key (paciente) references Paciente(id),
 constraint car_pac_fk foreign key (cardapio) references Cardapio(id)
);

create table Paciente_Atividade (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 atividade numeric(5),
 frequencia numeric(5),
 constraint pac_ati_fk foreign key (paciente) references Paciente(id),
 constraint paa_ati_fk foreign key (atividade) references Atividade(id),
 constraint paa_fre_fk foreign key (frequencia) references FrequenciaAtividade(id)
);

create table Paciente_Patologia (
 id numeric(5) primary key,
 paciente numeric(5) not null,
 patologia numeric(5) not null,
 constraint per_pat_fk foreign key (paciente) references Paciente(id),
 constraint per_pac_fk foreign key (patologia) references Patologia(id)
);

create table Nutricionista_Cardapio (
 id numeric(5) primary key,
 nutricionista numeric(3) not null,
 cardapio numeric(3) not null,
 constraint nut_car_fk foreign key (nutricionista) references Nutricionista(id),
 constraint car_nut_fk foreign key (cardapio) references Cardapio(id)
);

create table Alimento_Nutriente (
 id numeric(5) primary key,
 alimento numeric(5) not null,
 nutriente numeric(5) not null,
 quantidade numeric(5,2) not null,
 constraint ali_nut_fk foreign key (alimento) references Alimento(id),
 constraint nut_ali_fk foreign key (nutriente) references Nutriente(id)
);

create table Alimento_Cardapio (
 id numeric(5) primary key,
 alimento numeric(5) not null,
 cardapio numeric(5) not null,
 quantidade numeric(5,2) not null,
 constraint ali_car_fk foreign key (alimento) references Alimento(id),
 constraint car_ali_fk foreign key (cardapio) references Cardapio(id)
);

create table Alimento_Patologia (
 id numeric(5) primary key,
 alimento numeric(5) not null,
 patologia numeric(5) not null,
 constraint ali_pat_fk foreign key (alimento) references Alimento(id),
 constraint pat_ali_fk foreign key (patologia) references Patologia(id)
);

create table Categoria_Patologia (
 id numeric(5) primary key,
 categoria numeric(5) not null,
 patologia numeric(5) not null,
 constraint cat_pat_fk foreign key (categoria) references Categoria(id),
 constraint pat_cat_fk foreign key (patologia) references Patologia(id)
);

create table Grupo_Patologia (
 id numeric(5) primary key,
 grupo numeric(5) not null,
 patologia numeric(5) not null,
 constraint gru_pat_fk foreign key (grupo) references GrupoAlimentar(id),
 constraint pat_gru_fk foreign key (patologia) references Patologia(id)
);

create table Nutriente_Patologia (
 id numeric(5) primary key,
 nutriente numeric(5) not null,
 patologia numeric(5) not null,
 constraint nut_pat_fk foreign key (nutriente) references Nutriente(id),
 constraint pat_nut_fk foreign key (patologia) references Patologia(id)
);


-- Tabelas de apoio à aplicação --

create table LogAcoes (
 id numeric(5) primary key,
 tipo varchar(30) not null,
 data date not null,
 hora time not null,
 responsavel numeric(5) not null,
 perfil numeric(5) not null,
 acao varchar(200) not null,
 constraint log_usu_fk foreign key (responsavel) references Usuario(id),
 constraint log_per_fk foreign key (perfil) references PerfilAcesso(id)
);