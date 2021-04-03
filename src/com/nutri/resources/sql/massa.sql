/* MASSA DE DADOS */

-- Perfis de acesso --
insert into perfilacesso values (1, 'adm');
insert into perfilacesso values (2, 'nut');
insert into perfilacesso values (3, 'pac');

-- Logins --
insert into login values (1, 'milena', '12345678');
insert into login values (2, 'elina', '12345678');
insert into login values (3, 'henrique', '12345678');

-- Usuários --
insert into usuario values (1, 'Milena Croft', 'milena@nutri.com', 1);
insert into usuario values (2, 'Elina Kazue', 'elina@nutri.com', 2);
insert into usuario values (3, 'Henrique Marin', 'henrique@nutri.com', 3);

-- Endereços --
insert into endereco values (1, 'R 10', '1', null, 'Centro', 'Suzano', 'SP', 'Brasil', 08080000, 2);

-- Telefones --
insert into telefone values (1, 11, 47462626, 2);
insert into telefone values (2, 11, 90901010, 2);

-- Administradores --
insert into administrador values (1, 1);

-- Nutricionistas --
insert into nutricionista values (1, '26555', 1, 2);

-- Perfis de paciente --
insert into perfilpaciente values (1, 'CRI_ATI_CAR');
insert into perfilpaciente values (2, 'CRI_SED_CAR');
insert into perfilpaciente values (3, 'CRI_ATI_VEG');
insert into perfilpaciente values (4, 'CRI_SED_VEG');
insert into perfilpaciente values (5, 'H_ADU_ATI_CAR');
insert into perfilpaciente values (6, 'H_ADU_SED_CAR');
insert into perfilpaciente values (7, 'H_ADU_ATI_VEG');
insert into perfilpaciente values (8, 'H_ADU_SED_VEG');
insert into perfilpaciente values (9, 'M_ADU_ATI_CAR');
insert into perfilpaciente values (10, 'M_ADU_SED_CAR');
insert into perfilpaciente values (11, 'M_ADU_ATI_VEG');
insert into perfilpaciente values (12, 'M_ADU_SED_VEG');

-- Pacientes --
insert into paciente values (1, 3, '1987-10-03', 70, 1.7, 0, 6);

-- Freqüências --
insert into frequenciaatividade values (1, 'Baixa');
insert into frequenciaatividade values (2, 'Média');
insert into frequenciaatividade values (3, 'Alta');

-- Atividades --
insert into atividade values (1, 'Caminhada');
insert into atividade values (2, 'Atletismo');
insert into atividade values (3, 'Natação');

-- Patologias --
insert into patologia values (1, 'Diabetes', 'Restrição de açúcar; Defeito na produção de insulina;');

-- Grupos alimentares --
insert into grupoalimentar values (1, 'Cereais e tubérculos');
insert into grupoalimentar values (2, 'Hortaliças');
insert into grupoalimentar values (3, 'Frutas');
insert into grupoalimentar values (4, 'Laticínios');
insert into grupoalimentar values (5, 'Carnes e ovos');
insert into grupoalimentar values (6, 'Leguminosas');
insert into grupoalimentar values (7, 'Óleos e gorduras');
insert into grupoalimentar values (8, 'Açúcares e doces');

-- Categorias --
insert into categoria values (1, 'Pães', 1);
insert into categoria values (2, 'Cereais', 1);
insert into categoria values (3, 'Raízes', 1);
insert into categoria values (4, 'Tubérculos', 1);
insert into categoria values (5, 'Verduras', 2);
insert into categoria values (6, 'Legumes', 2);
insert into categoria values (7, 'Vermelhas', 3);
insert into categoria values (8, 'Cítricas', 3);
insert into categoria values (9, 'Leite', 4);
insert into categoria values (10, 'Queijo', 4);
insert into categoria values (11, 'Carnes', 5);
insert into categoria values (12, 'Ovos', 5);
insert into categoria values (13, 'Grãos', 6);
insert into categoria values (14, 'Óleos', 7);
insert into categoria values (15, 'Gorduras', 7);
insert into categoria values (16, 'Açúcares', 8);
insert into categoria values (17, 'Doces', 8);

-- Medidas --
insert into medida values (1, 'unidade', 100);
insert into medida values (2, 'colher', 30);
insert into medida values (3, 'xicara', 150);
insert into medida values (4, 'folha', 10);
insert into medida values (5, 'fatia', 50);
insert into medida values (6, 'cacho', 120);
insert into medida values (7, 'concha', 200);

-- Alimentos --
insert into alimento values (1, 'Pão francês', 1, 1);
insert into alimento values (2, 'Arroz branco cozido', 2, 2);
insert into alimento values (3, 'Mandioca frita', 3, 3);
insert into alimento values (4, 'Salada de batatas', 4, 3);
insert into alimento values (5, 'Alface', 5, 4);
insert into alimento values (6, 'Beterraba', 6, 5);
insert into alimento values (7, 'Morango', 7, 1);
insert into alimento values (8, 'Laranja', 8, 1);
insert into alimento values (9, 'Leite integral', 9, 3);
insert into alimento values (10, 'Mussarela', 10, 5);
insert into alimento values (11, 'Bife grelhado', 11, 1);
insert into alimento values (12, 'Ovo frito', 12, 1);
insert into alimento values (13, 'Feijão preto cozido', 13, 2);
insert into alimento values (14, 'Azeite de oliva', 14, 2);
insert into alimento values (15, 'Margarina', 15, 2);
insert into alimento values (16, 'Açúcar branco refinado', 16, 2);
insert into alimento values (17, 'Geléia de goiaba', 17, 2);

-- Nutrientes --
insert into nutriente values (1, 'Caloria');

-- Históricos alimentares --
insert into historicoalimentar values (1, 1, 1, 1.5, '2009-10-03', '08:00:00');
insert into historicoalimentar values (2, 1, 8, 3, '2009-10-03', '10:00:00');
insert into historicoalimentar values (3, 1, 2, 8, '2009-10-03', '12:00:00');
insert into historicoalimentar values (4, 1, 12, 2, '2009-10-03', '12:00:00');
insert into historicoalimentar values (5, 1, 7, 10, '2009-10-03', '15:00:00');
insert into historicoalimentar values (6, 1, 2, 6, '2009-10-03', '21:00:00');
insert into historicoalimentar values (7, 1, 4, 3, '2009-10-03', '21:00:00');

-- Cardápios --

-- Resultados de análises --

-- Login x Perfil de acesso --
insert into login_perfilacesso values (1, 1, 1);
insert into login_perfilacesso values (2, 2, 2);
insert into login_perfilacesso values (3, 3, 3);

-- Paciente x Nutricionista --

-- Paciente x Cardápio --

-- Paciente x Atividade --
insert into paciente_atividade values (1, 1, null, null);

-- Paciente x Patologia --

-- Nutricionista x Cardápio --

-- Alimento x Nutriente --
insert into alimento_nutriente values (1, 1, 1, 67.5);
insert into alimento_nutriente values (2, 2, 1, 49.2);
insert into alimento_nutriente values (3, 3, 1, 528);
insert into alimento_nutriente values (4, 4, 1, 216.5);
insert into alimento_nutriente values (5, 5, 1, 2);
insert into alimento_nutriente values (6, 6, 1, 22);
insert into alimento_nutriente values (7, 7, 1, 4);
insert into alimento_nutriente values (8, 8, 1, 46);
insert into alimento_nutriente values (9, 9, 1, 93.75);
insert into alimento_nutriente values (10, 10, 1, 145);
insert into alimento_nutriente values (11, 11, 1, 235);
insert into alimento_nutriente values (12, 12, 1, 190);
insert into alimento_nutriente values (13, 13, 1, 21);
insert into alimento_nutriente values (14, 14, 1, 270);
insert into alimento_nutriente values (15, 15, 1, 222);
insert into alimento_nutriente values (16, 16, 1, 120);
insert into alimento_nutriente values (17, 17, 1, 60);

-- Alimento x Cardápio --

-- Alimento x Patologia --

-- Categoria x Patologia --

-- Grupo x Patologia --
insert into grupo_patologia values (1, 8, 1);

-- Nutriente x Patologia --

-- LogAcoes --