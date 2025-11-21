# 📌 Eventify – Application de Gestion d'Événements (Spring Boot + Spring Security)

Eventify est une application backend permettant de gérer des événements avec différents rôles utilisateurs.  
Elle intègre une sécurité avancée grâce à **Spring Security**, une architecture **stateless**, et une gestion complète des rôles et autorisations.

---

## 🚀 Contexte du Projet

Eventify permet :

- Aux **utilisateurs** de s’inscrire et de participer à des événements
- Aux **organisateurs** de créer et gérer leurs événements
- À l’**administrateur** de superviser l’ensemble du système

L’objectif du projet est de **sécuriser l’application** en utilisant **Spring Security (Authentication Basic)** et une gestion stricte des rôles.

---

# 🛡️ Rôles & Authentification

L’application gère trois rôles principaux :

- `ROLE_USER`
- `ROLE_ADMIN`
- `ROLE_ORGANIZER`

Caractéristiques :

- Rôle par défaut à l’inscription : **ROLE_USER**
- Les rôles sont stockés en base au format `ROLE_XXX`
- Un utilisateur possède **un seul rôle**
- L’administrateur peut modifier le rôle d’un utilisateur

**Authentification utilisée :**  
➡ Basic Authentication (`Authorization: Basic base64(email:password)`)

---

# 📚 Exigences Fonctionnelles

## 🔓 Endpoints publics (sans authentification)

| Méthode | Endpoint              | Description                                 |
|--------|------------------------|---------------------------------------------|
| POST   | `/api/public/users`   | Inscription d’un nouvel utilisateur         |
| GET    | `/api/public/events`  | Liste des événements publics                |

---

## 👤 USER

| Méthode | Endpoint                                 | Description                         |
|--------|-------------------------------------------|-------------------------------------|
| GET    | `/api/user/profile`                       | Profil utilisateur                  |
| POST   | `/api/user/events/{id}/register`          | Inscription à un événement          |
| GET    | `/api/user/registrations`                 | Liste des inscriptions              |

---

## 🧑‍💼 ORGANIZER

| Méthode | Endpoint                                 | Description                         |
|--------|-------------------------------------------|-------------------------------------|
| POST   | `/api/organizer/events`                    | Créer un événement                  |
| PUT    | `/api/organizer/events/{id}`               | Modifier un événement               |
| DELETE | `/api/organizer/events/{id}`               | Supprimer un événement              |

---

## 🛠️ ADMIN

| Méthode | Endpoint                                | Description                        |
|--------|------------------------------------------|------------------------------------|
| GET    | `/api/admin/users`                       | Liste des utilisateurs             |
| PUT    | `/api/admin/users/{id}/role`             | Modifier le rôle d’un utilisateur  |
| DELETE | `/api/admin/events/{id}`                 | Supprimer un événement             |

---

# 🔧 Exigences Techniques – Spring Security

## ✔ CustomAuthenticationProvider
- Basé sur un **UserDetailsService personnalisé**
- Vérification du mot de passe avec **BCryptPasswordEncoder**

## ✔ Configuration Spring Security

- Architecture **stateless**
- `SessionCreationPolicy.STATELESS`
- Désactivation de **CSRF**
- Règles d'accès :

```text
/api/public/**     → accessible sans authentification  
/api/user/**       → hasRole("USER")  
/api/organizer/**  → hasRole("ORGANIZER")  
/api/admin/**      → hasRole("ADMIN")
