Clínica de Consulta Ágil

Este sistema foi desenvolvido como parte do Desafio do Projeto Aceleradora Ágil para gerenciar uma clínica de consultas. Oferece funcionalidades essenciais como cadastro de pacientes, marcação e cancelamento de consultas, garantindo a persistência dos dados mesmo após o encerramento do programa.

Funcionalidades

Cadastrar um paciente: Permite registrar novos pacientes na clínica, verificando se já estão cadastrados para evitar duplicidades.

Marcar uma consulta: Permite agendar consultas para pacientes existentes, escolhendo data, hora e especialidade. O sistema valida se o horário selecionado está disponível.

Cancelar uma consulta: Permite remover consultas agendadas, ajudando na gestão de horários disponíveis na clínica.

Sair: Opção para encerrar o programa.

Persistência de Dados

Os dados são persistidos utilizando o mecanismo de serialização do Java, salvando-os no arquivo consultas.csv. Isso garante que as informações de pacientes e consultas sejam preservadas entre diferentes execuções do sistema.

Validação de Dados

Cadastro de Pacientes: Verifica se o paciente já está cadastrado pelo número de telefone, evitando duplicidades na base de dados.

Marcação de Consultas: Previne agendamentos em horários já ocupados e impede o agendamento de consultas retroativas.

Menu Principal

Ao iniciar o sistema, você é recebido pelo menu principal, onde pode escolher entre as seguintes opções:

Cadastro de Pacientes: Insira nome e telefone do paciente para registro.

Marcar uma Consulta: Selecione um paciente cadastrado, defina data, hora e especialidade para agendar uma consulta.

Cancelar uma Consulta: Escolha uma consulta agendada para cancelá-la.

Sair: Encerra o programa.