# RentTools Frontend

Stack: HTML + CSS + JavaScript + Bootstrap 5.

Justificación Bootstrap 5: entrega layout responsivo y componentes listos sin añadir frameworks SPA ni dependencias pesadas.

## Configuración baseURL
- En la pantalla de login puedes cambiar la Base URL y guardarla en `localStorage`.
- Por defecto se usa `http://localhost:8080`.

## Correr en local
1. Abre `frontend/index.html` con Live Server o cualquier servidor estático.
2. Asegúrate de que el backend esté corriendo.

## Probar roles
- Roles soportados según OpenAPI: `ADMIN`, `PROVIDER`, `CUSTOMER`.
- El login y register devuelven `token`, `userId`, `role` y se guarda en `localStorage`.
- Al iniciar sesión se redirige a la vista según rol.

## Convenciones y decisiones
- `Authorization: Bearer <token>` en cada request.
- Decodificación de JWT sin librerías para leer `exp`.
- Auto-logout cuando `exp` ha expirado.
- Tablas con paginación, orden y filtro en el cliente.
- Formularios con validaciones básicas en cliente.

## TODOs (faltantes en OpenAPI)
- Listado de rentals para Provider (no existe endpoint para listar).
- Endpoints que indiquen roles explícitos en OpenAPI (security no definido).
- Endpoints de pagos con creación explícita (solo existe `POST /api/customer/rentals/{id}/pay`).

