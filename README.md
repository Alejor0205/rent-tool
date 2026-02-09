# RentTool — Sistema de alquiler de herramientas

RentTool es una plataforma para **gestión de alquiler de herramientas** con **backend en Java 17 + Spring Boot** y **frontend web estático** (HTML/CSS/JS). Maneja catálogo, alquileres, pagos, métricas y roles con autenticación JWT.

---

## 1) Visión general

- **Backend**: Spring Boot + Spring Security (JWT) + Spring Data JPA + MySQL
- **Frontend**: HTML + CSS + JavaScript + Bootstrap 5 (sin framework SPA)
- **Comunicación**: REST API (OpenAPI en `/v3/api-docs`)

---

## 2) Estructura del proyecto

```
./
  src/                  # Backend Spring Boot
  frontend/             # Frontend estático
    assets/
      css/
      js/
      img/
    pages/
  pom.xml
```

---

## 3) Requisitos

### Backend
- Java 17
- Maven
- MySQL local

### Frontend
- Navegador moderno
- Live Server (VS Code) o servidor estático

---

## 4) Backend: cómo ejecutar

Desde la raíz del repo:

```powershell
.\mvnw spring-boot:run
```

URL por defecto:

```
http://localhost:8080
```

---

## 5) Frontend: cómo ejecutar

**Recomendado**: abrir con Live Server el archivo:

```
frontend/index.html
```

URL esperada:

```
http://localhost:5500/frontend/index.html
```

Si abres Live Server desde la raíz, entra manualmente a:

```
http://localhost:5500/frontend/index.html
```

---

## 6) Configuración del API (baseURL)

En `frontend/pages/login.html` puedes definir la Base URL del backend.
- Valor por defecto: `http://localhost:8080`
- Se guarda en `localStorage`

---

## 7) Autenticación y roles

- `POST /api/auth/login` y `POST /api/auth/register` retornan:
  - `token`
  - `userId`
  - `role`
- El token se guarda en `localStorage`.
- Se envía en cada request como:
  - `Authorization: Bearer <token>`
- Se controla expiración (`exp`) y se cierra sesión automáticamente.

**Roles definidos en OpenAPI:**
- `ADMIN`
- `PROVIDER`
- `CUSTOMER`

---

## 8) Navegación por rol (frontend)

Al iniciar sesión el usuario es redirigido a:
- `ADMIN` ? `/frontend/pages/admin-users.html`
- `PROVIDER` ? `/frontend/pages/provider-tools.html`
- `CUSTOMER` ? `/frontend/pages/client-explore.html`

---

## 9) Módulos principales

### Admin
- Usuarios
- Rentals (global)
- Payments (global)
- Damage reports
- Métricas

### Provider
- Herramientas (CRUD + estado + imagen)
- Flujo de rentals por ID

### Customer
- Explorar herramientas disponibles
- Crear reserva
- Historial + pagos + facturas

---

## 10) Endpoints clave (resumen)

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### Categories
- `GET /api/categories`
- `POST /api/categories`
- `GET /api/categories/{id}`
- `DELETE /api/categories/{id}`

### Tools
- `GET /api/tools`
- `POST /api/tools`
- `GET /api/tools/{id}`
- `PUT /api/tools/{id}`
- `DELETE /api/tools/{id}`
- `GET /api/tools/available`
- `PATCH /api/tools/{id}/status`
- `GET /api/tools/{id}/image`
- `POST /api/tools/{id}/image`

### Rentals
- `POST /api/rentals`
- `GET /api/rentals/{id}`
- `PUT /api/rentals/{id}`
- `DELETE /api/rentals/{id}`
- `PATCH /api/rentals/{id}/accept`
- `PATCH /api/rentals/{id}/reject`
- `PATCH /api/rentals/{id}/return`
- `GET /api/rentals/{id}/invoice`

### Customer
- `GET /api/customer/rentals`
- `POST /api/customer/rentals`
- `POST /api/customer/rentals/{id}/pay`

### Provider
- `PATCH /api/provider/rentals/{id}/accept`
- `PATCH /api/provider/rentals/{id}/reject`
- `PATCH /api/provider/rentals/{id}/return`

### Payments
- `GET /api/payments/{id}`
- `PUT /api/payments/{id}`
- `DELETE /api/payments/{id}`

### Admin
- `GET /api/admin/users`
- `POST /api/admin/users`
- `GET /api/admin/users/{id}`
- `PUT /api/admin/users/{id}`
- `DELETE /api/admin/users/{id}`
- `GET /api/admin/rentals`
- `GET /api/admin/payments`
- `GET /api/admin/damage-reports`
- `PATCH /api/admin/damage-reports/{id}/resolve`
- `GET /api/admin/metrics/top-tools`
- `GET /api/admin/metrics/profitability`
- `GET /api/admin/metrics/income`

---

## 11) Buenas prácticas aplicadas

- API Client centralizado (`frontend/assets/js/api.js`)
- Manejo uniforme de errores (401/403/5xx)
- Expiración JWT y auto-logout
- Navegación modular por rol con layout reutilizable (`components.html` + `layout.js`)
- UI responsiva y consistente

---

## 12) Limitaciones actuales (OpenAPI)

- No existe endpoint para listar rentals del Provider
- OpenAPI no declara `security` por rol
- Pagos no exponen `POST /api/payments` (solo `POST /api/customer/rentals/{id}/pay`)

---

## 13) Soporte rápido

**Si aparece:**

```
Cannot GET /pages/... 
```

Entra a:

```
http://localhost:5500/frontend/index.html
```

---

Fin del README.
