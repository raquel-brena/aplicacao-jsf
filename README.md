
# Aplicação utilizando Java Server Faces

<p align="center">
  <img src="https://github.com/raquel-brena/esig/blob/master/src/main/webapp/resources/login.png" width="200" title="JSF logo">
  <div align="center">
    <a href="https://raquel-brena.github.io/" title="Report Bug">Portfólio</a> ·
    <a href="https://github.com/raquel-brena/TuneTown/issues" title="Report Bug">Report Bug</a> ·
    <a href="https://github.com/raquel-brena/TuneTown/issues" title="Request Feature">Request Feature</a>
  </div>
</p>

> Projeto desenvolvido utilizando Java Server Faces (JSF)  para gerenciamento de pessoas e usuários dentro de um sistema web corporativo.

---

## 📝 Sobre

Essa aplicação tem como objetivo principal realizar o cálculo do salário de cada pessoa cadastrada, a partir dos vencimentos fornecidos. 
Além disso, permite o cadastro, listagem e exclusão das pessoas, usuários e salários consolidados, possuindo autenticação via login e senha. 
O cálculo dos salários é feito de modo assincrono utilizando CompleteFuture e criação de uma Thread. Ademais, o design é simples inspirado em aplicações 
já consolidadas no mercado e conta com tratamento de exceções eficiente.
Para essa aplicação, utilizei JSF juntamente com Java EE e JPA puro, sem a utilização de Hibernate. 

![cover](https://github.com/user-attachments/assets/a9c1f94f-1ebe-4fba-b6b7-392fca7c67d6)

---

## 🚀 Tecnologias

As principais tecnologias usadas neste projeto são:
- **JSF**
- **Java EE**
- **PrimeFaces**
- **Postgres**
- **Docker**
- **Apache Tomcat**
- **Intellij**

---

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- **Docker**
- **JAVA JDK 11**
- **MAVEN**

### Instalação e Execução

Para rodar o projeto localmente, siga os passos abaixo:

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/raquel-brena/aplicacao-jsf.git
   ```

2. **Navegue até o diretório do projeto:**
   ```bash
   cd aplicacao-jsf
   ```

3. **Inicie o projeto com Docker:**
   ```bash
   docker compose up -d
   ```
   
4. **Verifique se o container do postgres está startado:**
   ```bash
   docker compose ps
   ```

5. **Compile a aplicação:**
   ```
   mvn clean install
   ```
6. **Starte o servidor **
   ```
   clique em "Run Tomcat" no Intellij para iniciar a aplicação.
   ```
---


## 👥 Autora

- **Raquel Brena Silva de Lima** - [GitHub](https://github.com/raquel-brena)

---

[🔼 Voltar ao topo](#Aplicação-utilizando-java-server-faces)
