## 🎯 Objetivo do Projeto
Este projeto foi desenvolvido como parte de um processo seletivo na empresa Hexagon. O objetivo principal foi criar um aplicativo offline first CRUD Android utilizando Jetpack Compose e MVVM.

## 🛠 Tecnologias Utilizadas

- IDE: Android Studio
- Linguagem: Kotlin
- Kotlin DSL (build.gradle.kts) para configuração de build
- Jetpack Compose: Para a criação de interfaces de usuário e animações
- MVVM: Padrão de arquitetura para separar a lógica de apresentação
- Clean Architecture: Estrutura para manter o código modular e escalável
- Hilt: Para injeção de dependências
- Room: Para persistência de dados local
- Kotlin Coroutines: Para gerenciamento de threads

## 🚦 Hard Skills Aprendidas

- **Desenvolvimento com Jetpack Compose: Criação de UI moderna e declarativa.**
- **Implementação do MVVM: Separação da lógica de apresentação e dados.**
- **Aplicação da Clean Architecture: Organização do código em camadas para melhor manutenção e escalabilidade.**
- **Persistência de Dados com Room: Armazenamento de dados locais com facilidade.**


## Arquitetura
![image](https://github.com/user-attachments/assets/195ed6fb-d560-4a13-8300-4801bf5f1088)


## Funcionalidades do Aplicativo


1. **Tela Principal**: 
   - Apresenta um filtro para ordenar a lista de pessoas por nome ou idade, tanto em ordem crescente quanto decrescente. O filtro só é exibido ao clicar no ícone correspondente e é ocultado ao clicar novamente.
   - Exibe a lista de pessoas em cards organizados com a imagem, nome e idade.
   - Permite adicionar, editar e excluir registros de pessoas.
   - Permite que o usuário desfaça a remoção de uma pessoa.
  
     ![image](https://github.com/user-attachments/assets/bd2a55e2-9d01-4f56-b0c5-f00ea67f8c1b)



2. **Tela de Edição**:
   - Permite ao usuário adicionar ou modificar a imagem de perfil a partir da galeria.
   - Oferece campos para editar nome, idade, CPF e cidade.
   - Inclui validação para garantir que todos os campos sejam preenchidos corretamente.
   - Atualiza o registro na base de dados local após as alterações.
  
     ![image](https://github.com/user-attachments/assets/0df2f898-4d42-421a-a19d-2cab89e640a8)


3. **Tela de Adicionar**:
   - Permite ao usuário adicionar uma nova imagem de perfil a partir da galeria.
   - Oferece campos para inserir nome, idade, CPF e cidade.
   - Inclui validação para assegurar que todos os campos sejam preenchidos antes de salvar.
   - Atualiza a base de dados local com o novo registro após a inclusão.

     ![image](https://github.com/user-attachments/assets/1d3308bd-faa5-4bda-a9eb-2e99425dc76d)


## Observação 
Código está na branch Master
