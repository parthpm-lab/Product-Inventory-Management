# Product Inventory Management System

A modern, full-stack inventory management application built with **Spring Boot** and **Angular** that enables organizations to efficiently manage products, suppliers, purchase orders, and inventory transactions with role-based access control and real-time monitoring.

---

## Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [System Architecture](#system-architecture)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [API Documentation](#api-documentation)
- [Frontend Components](#frontend-components)
- [Database Schema](#database-schema)
- [Authentication & Security](#authentication--security)
- [User Roles & Permissions](#user-roles--permissions)
- [Key Workflows](#key-workflows)
- [Testing](#testing)
- [Contributing](#contributing)

---

## Overview

The **Product Inventory Management System** is a comprehensive solution designed to streamline inventory operations through:

- **Centralized Product Management**: Track products, categories, stock levels, and expiry dates
- **Supplier Management**: Maintain supplier information and performance metrics
- **Purchase Order Workflow**: Create, approve, and receive purchase orders with automated stock updates
- **Transaction Tracking**: Record and monitor all inventory movements (purchases, sales, returns)
- **Stock Alerts**: Automatic notifications for low-stock items
- **Analytics & Reporting**: Historical trends and real-time dashboard metrics
- **User Access Control**: Role-based permissions for different organizational levels

---

##  Technology Stack

### Backend
| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 3.3.5 |
| **Language** | Java | 21 |
| **Build Tool** | Maven | Latest |
| **Database** | PostgreSQL | Latest |
| **ORM** | Spring Data JPA / Hibernate | Latest |
| **Authentication** | Spring Security + JWT | JWT JJWT 0.12.6 |
| **Password Encoding** | BCrypt | - |
| **Entity Mapping** | ModelMapper | 3.2.1 |

### Frontend
| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Angular | 18.2.0 |
| **Language** | TypeScript | Latest |
| **Styling** | CSS + Bootstrap | Latest |
| **HTTP Client** | Angular HttpClient | 18.2.0 |
| **State Management** | RxJS | 7.8.0 |
| **Charts** | @swimlane/ngx-charts | 20.5.0 |
| **Encryption** | crypto-js | 4.2.0 |
| **Testing** | Karma + Jasmine | Latest |

### DevOps & Infrastructure
- **CORS**: Enabled for cross-origin requests
- **File Upload**: Max 2GB support
- **Image Storage**: Filesystem-based (../frontend/public/products/)
- **Port Configuration**: Backend (5050), Frontend (4200)

---

## System Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   FRONTEND (Angular)                    │
│  (Dashboard | Products | Suppliers | PO | Transactions)│
└──────────────────────────┬──────────────────────────────┘
                           │
                   HTTP/REST (Port 4200)
                           │
┌──────────────────────────▼──────────────────────────────┐
│            API Gateway / Security Filter                │
│              (JWT Validation, CORS)                     │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│          REST Controllers (11 Controllers)              │
│  Auth | Product | Category | Supplier | PurchaseOrder  │
│  Transaction | Dashboard | Alert | Analytics | User    │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│             Service Layer (9 Services)                  │
│  User | Product | Transaction | PurchaseOrder |        │
│  Supplier | Category | StockAlert | Dashboard |Analytics│
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│          Data Access Layer (JPA Repositories)           │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│         PostgreSQL Database (HCL-LMS)                   │
│  (Users | Products | Categories | Suppliers |          │
│   Transactions | PurchaseOrders | StockAlerts)          │
└──────────────────────────────────────────────────────────┘
```

---

##  Features

### 1. **User Authentication & Authorization**
- User registration and login with JWT tokens
- 4 user roles with granular permissions
- 6-month JWT token expiration
- Client-side token encryption (AES)
- Role-based route guards and method-level security

### 2. **Product Management**
- Create, read, update, delete products
- SKU uniqueness enforcement
- Product categorization
- Stock quantity tracking
- Expiry date management
- Product image upload and storage
- Pagination support

### 3. **Supplier Management**
- Maintain supplier profiles
- Track supplier contact information
- Supplier status (ACTIVE/INACTIVE)
- Performance metrics and supplier profiles
- Procurement officer assignment

### 4. **Purchase Order Workflow**
- Create purchase orders with items and quantities
- Automatic PO number generation (PO-YYYY-NNN format)
- PO status tracking (DRAFT, SUBMITTED, APPROVED, RECEIVED)
- Priority-based ordering
- Admin approval workflow
- Automatic stock updates upon goods receipt
- Warehouse manager confirmation

### 5. **Inventory Transactions**
Three types of transactions with comprehensive tracking:
- **PURCHASE**: Restocking transactions (increase stock)
- **SELL**: Customer sales transactions (decrease stock)
- **RETURN**: Return to supplier transactions
- Status tracking (COMPLETED, CANCELLED, PENDING)
- Price and quantity validation

### 6. **Stock Alerts**
- Automatic low-stock alert generation (default threshold: 10 units)
- Alert status tracking (PENDING, RESOLVED)
- Customizable stock thresholds
- Alert resolution tracking
- Dashboard widget for pending alerts

### 7. **Analytics & Reporting**
- Real-time dashboard with key metrics:
  - Low stock product count
  - Pending purchase orders
  - Active alerts
- Historical transaction analytics
- Monthly and yearly trends
- Transaction type breakdown (purchases, sales, returns)
- Advanced charting and visualization

### 8. **Dashboard & Monitoring**
- Real-time metrics polling (10-second intervals)
- Low-stock alerts widget
- Pending PO counter
- Active transaction count
- Quick access navigation

---

##  Prerequisites

### System Requirements
- **Java**: JDK 21 or higher
- **Node.js**: 18.x or higher
- **npm**: 9.x or higher
- **PostgreSQL**: 12 or higher
- **Maven**: 3.8 or higher (or use mvnw)

### Development Tools (Optional)
- **IDE**: IntelliJ IDEA, VS Code, or Eclipse
- **Git**: For version control
- **Postman**: For API testing
- **DBeaver**: For database management

---

##  Installation & Setup

### Step 1: Clone the Repository

```bash
git clone <repository-url>
cd HCL-LMS--master
```

### Step 2: Database Setup

1. **Ensure PostgreSQL is running** on localhost:5432

2. **Create the database**:
   ```sql
   CREATE DATABASE "HCL-LMS";
   ```

3. **Update database credentials** in `backend/src/main/resources/application.properties` if different from:
   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=2004
   ```

### Step 3: Backend Setup

Navigate to the backend directory:

```bash
cd backend
```

**Install dependencies & compile**:
```bash
mvn clean install
```

Or on Windows, use the provided Maven wrapper:
```bash
mvnw.cmd clean install
```

### Step 4: Frontend Setup

Navigate to the frontend directory:

```bash
cd ../frontend
```

**Install dependencies**:
```bash
npm install
```

---

## ⚙️ Configuration

### Backend Configuration

**File**: `backend/src/main/resources/application.properties`

```properties
# Server Configuration
server.port=5050
spring.application.name=InventoryManagementSystem

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/HCL-LMS
spring.datasource.username=postgres
spring.datasource.password=2004

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
secreteJwtString=phegondev123456789phegondev123456789

# File Upload Configuration
spring.servlet.multipart.max-file-size=2GB
spring.servlet.multipart.max-request-size=2GB

# Image Storage Path
ims.upload.images-relative=../frontend/public/products/

# Stock Alert Threshold
ims.low-stock-threshold=10

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

### Frontend Configuration

**File**: `frontend/src/app/service/api.service.ts`

```typescript
// API Base URL
private API_ORIGIN = 'http://localhost:5050';
private BASE_URL = `${this.API_ORIGIN}/api`;

// Encryption
private ENCRYPTION_KEY = 'phegon-dev-inventory';
```

---

## ▶ Running the Application

### Step 1: Start the Backend Server

**From the backend directory**:

Using Maven:
```bash
mvn spring-boot:run
```

Or with Maven wrapper:
```bash
mvnw.cmd spring-boot:run
```

Expected output:
```
Tomcat started on port(s): 5050 (http)
```

### Step 2: Start the Frontend Development Server

**From the frontend directory**:

```bash
npm start
```

Or:
```bash
ng serve
```

Expected output:
```
Application bundle generation complete. [X.XXX seconds]
Application running on http://localhost:4200/
```

### Step 3: Access the Application

Open your browser and navigate to:
```
http://localhost:4200
```

### Default Credentials (for testing)

> **Note**: Create these users through the registration page or database seeding

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@hcl.com | admin123 |
| Procurement Officer | procurement@hcl.com | procurement123 |
| Warehouse Manager | warehouse@hcl.com | warehouse123 |
| Staff | staff@hcl.com | staff123 |

---

##  Project Structure

### Backend Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/phegondev/InventoryManagementSystem/
│   │   │   ├── controller/           (11 REST Controllers)
│   │   │   ├── service/              (9 Service Interfaces + Implementations)
│   │   │   ├── entity/               (10 JPA Entities)
│   │   │   ├── dto/                  (Data Transfer Objects)
│   │   │   ├── repository/           (JPA Repositories)
│   │   │   ├── security/             (JWT & Security Config)
│   │   │   ├── config/               (Spring Configuration)
│   │   │   ├── exception/            (Custom Exceptions & Handlers)
│   │   │   ├── enums/                (Enums: UserRole, TransactionType, etc.)
│   │   │   └── InventoryManagementSystemApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/...
├── pom.xml
├── mvnw & mvnw.cmd        (Maven Wrapper)
└── HELP.md
```

### Frontend Structure

```
frontend/
├── src/
│   ├── main.ts                    (Application entry point)
│   ├── index.html                 (HTML template)
│   ├── styles.css                 (Global styles)
│   ├── app/
│   │   ├── app.component.*        (Root component)
│   │   ├── app.config.ts          (Angular config)
│   │   ├── app.routes.ts          (Route definitions)
│   │   ├── service/               (API & Guard services)
│   │   ├── login/                 (Authentication)
│   │   ├── register/              (User registration)
│   │   ├── dashboard/             (Home dashboard)
│   │   ├── product/               (Product management)
│   │   ├── category/              (Category management)
│   │   ├── supplier/              (Supplier management)
│   │   ├── purchase-orders/       (PO workflow)
│   │   ├── purchase/              (Purchase transactions)
│   │   ├── sell/                  (Sales transactions)
│   │   ├── transaction/           (Transaction history)
│   │   ├── stock-alerts/          (Alert management)
│   │   ├── analytics/             (Charts & reports)
│   │   ├── profile/               (User profile)
│   │   └── shared/                (Shared utilities)
│   └── public/
│       └── products/              (Product images)
├── package.json
├── angular.json
├── tsconfig.json
└── karma.conf.js
```

---

## 🔌 API Documentation

### Base URL
```
http://localhost:5050/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secure_password",
  "phone": "1234567890",
  "role": "ADMIN"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "secure_password"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "user": { ... }
}
```

### Product Endpoints

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| POST | `/api/products/add` | Create product | ADMIN |
| PUT | `/api/products/update` | Update product | ADMIN, WAREHOUSE_MANAGER |
| GET | `/api/products/all` | List all products | All |
| GET | `/api/products/{id}` | Get product details | All |

### Supplier Endpoints

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| POST | `/api/suppliers` | Create supplier | ADMIN, PROCUREMENT_OFFICER |
| GET | `/api/suppliers` | List suppliers | All |
| GET | `/api/suppliers/{id}` | Get supplier details | All |
| PUT | `/api/suppliers/{id}` | Update supplier | ADMIN, PROCUREMENT_OFFICER |

### Purchase Order Endpoints

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| POST | `/api/purchase-orders` | Create PO | ADMIN, PROCUREMENT_OFFICER |
| GET | `/api/purchase-orders` | List POs (with optional status filter) | All |
| GET | `/api/purchase-orders/{id}` | Get PO details | All |
| PUT | `/api/purchase-orders/{id}/approve` | Approve PO | ADMIN |
| PUT | `/api/purchase-orders/{id}/receive` | Receive goods | WAREHOUSE_MANAGER, ADMIN |
| GET | `/api/purchase-orders/summary` | PO statistics | All |

### Transaction Endpoints

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| POST | `/api/transactions/purchase` | Record purchase | ADMIN, WAREHOUSE_MANAGER |
| POST | `/api/transactions/sell` | Record sale | ADMIN, STAFF |
| POST | `/api/transactions/return` | Return to supplier | ADMIN, WAREHOUSE_MANAGER |
| GET | `/api/transactions/all` | List transactions | All |
| GET | `/api/transactions/analytics` | Transaction analytics | All |

### Dashboard & Monitoring Endpoints

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| GET | `/api/dashboard/summary` | Dashboard metrics | All |
| GET | `/api/alerts` | List stock alerts | ADMIN, WAREHOUSE_MANAGER, PROCUREMENT_OFFICER |
| GET | `/api/analytics` | Advanced analytics | ADMIN |

---

##  Frontend Components

### Authentication Components
- **LoginComponent** - User login interface
- **RegisterComponent** - User registration form

### Main Features
- **DashboardComponent** - Real-time metrics and alerts
- **ProductComponent** - Product inventory listing
- **AddEditProductComponent** - Product creation/modification
- **CategoryComponent** - Category management
- **SupplierComponent** - Supplier management
- **PurchaseOrdersComponent** - Complete PO workflow
- **PurchaseComponent** - Record purchase transactions
- **SellComponent** - Record sales transactions
- **TransactionComponent** - Transaction history
- **TransactionDetailsComponent** - Detailed transaction view
- **StockAlertsComponent** - Stock alert management
- **AnalyticsComponent** - Advanced charting and analytics
- **ProfileComponent** - User profile management
- **PaginationComponent** - Reusable pagination control

### Services
- **ApiService** - HTTP client wrapper with JWT token management and encryption
- **GuardService** - Route protection and authentication validation

---

## 🗄️ Database Schema

### Core Entities

#### User
```sql
- id (PK)
- name
- email (unique)
- password (BCrypt hashed)
- phone
- role (ADMIN, PROCUREMENT_OFFICER, WAREHOUSE_MANAGER, STAFF)
- warehouse_id (FK)
- active (boolean)
- created_date
```

#### Product
```sql
- id (PK)
- name
- sku (unique)
- price (decimal)
- stockQuantity (integer)
- description
- imageUrl
- expiryDate (optional)
- categoryId (FK)
- created_date
- updated_date
```

#### Category
```sql
- id (PK)
- name (unique)
- description
```

#### Supplier
```sql
- id (PK)
- name
- address
- status (ACTIVE, INACTIVE)
- contact_person
- email
- phone
```

#### Transaction
```sql
- id (PK)
- totalProducts (integer)
- totalPrice (decimal)
- type (PURCHASE, SELL, RETURN)
- status (COMPLETED, CANCELLED, PENDING)
- userId (FK)
- productId (FK)
- supplierId (FK)
- transaction_date
- description
```

#### PurchaseOrder
```sql
- id (PK)
- poNumber (unique, format: PO-YYYY-NNN)
- supplierId (FK)
- status (DRAFT, SUBMITTED, APPROVED, RECEIVED)
- totalValue (decimal)
- priority (LOW, MEDIUM, HIGH)
- createdBy (FK to User)
- items (OneToMany to PurchaseOrderItem)
- created_date
- updated_date
```

#### PurchaseOrderItem
```sql
- id (PK)
- purchaseOrderId (FK)
- productId (FK)
- quantity (integer)
- unitPrice (decimal)
```

#### StockAlert
```sql
- id (PK)
- productId (FK)
- threshold (integer, default: 10)
- status (PENDING, RESOLVED)
- resolved_date (optional)
- created_date
```

---

##  Authentication & Security

### JWT Implementation
- **Algorithm**: HMAC-SHA256
- **Secret Key**: `phegondev123456789phegondev123456789`
- **Expiration**: 6 months (180 days)
- **Token Subject**: User email address

### Security Features
-  Spring Security configuration with stateless session management
-  BCrypt password encoding
-  JWT token validation on all protected endpoints
-  CORS enabled for localhost:4200
-  Client-side token encryption (AES)
-  Role-based method security (`@PreAuthorize`)
-  Custom authentication entry point and access denied handler
-  Secure token storage in encrypted localStorage

### Authentication Flow
1. User registers/logs in with credentials
2. Backend validates credentials and generates JWT token
3. Frontend encrypts token with AES encryption
4. Token stored in encrypted localStorage
5. Token sent in `Authorization: Bearer <token>` header
6. Backend validates token signature and expiration
7. Request processed based on user role and method-level security

---

##  User Roles & Permissions

### 1. **ADMIN**
- Full system access
- User management
- Approve purchase orders
- Receive goods from suppliers
- View all analytics and reports
- Manage alerts
- Configure system settings

### 2. **PROCUREMENT_OFFICER**
- Create and manage purchase orders
- Manage supplier information
- View product catalog
- Track transactions
- View analytics

### 3. **WAREHOUSE_MANAGER**
- Manage inventory and stock levels
- Receive goods from purchase orders
- Record purchase and return transactions
- View and resolve stock alerts
- Access warehouse dashboard

### 4. **STAFF**
- Record sales transactions
- View product information
- Access limited dashboard
- View transaction history

---

##  Key Workflows

### Workflow 1: Purchase Order Process
```
1. Procurement Officer creates PO
   ├─ Select supplier
   ├─ Add products and quantities
   └─ Submit for approval

2. Admin reviews and approves PO
   └─ PO status: DRAFT → APPROVED

3. Warehouse Manager receives goods
   ├─ Confirm receipt of items
   ├─ Stock automatically updated
   └─ PO status: APPROVED → RECEIVED
```

### Workflow 2: Stock Management
```
1. Product stock falls below threshold (10 units)
2. System automatically generates StockAlert
3. Alert appears on Dashboard and Stock Alerts page
4. Procurement Officer can create PO to restock
5. Warehouse Manager marks alert as RESOLVED after receipt
```

### Workflow 3: Sales Transaction
```
1. Staff member records customer sale
   ├─ Select product
   ├─ Enter quantity and price
   └─ Save transaction

2. System validates stock availability
3. Product stock quantity decreases
4. Transaction status: COMPLETED
5. Alert generated if stock falls below threshold
```

### Workflow 4: Return to Supplier
```
1. Warehouse Manager initiates return transaction
   ├─ Select supplier
   ├─ Select product and quantity
   └─ Save return transaction

2. System processes return
   ├─ Stock quantity increased
   └─ Transaction status: COMPLETED
```

---

##  Testing

### Backend Testing

**Run all tests**:
```bash
cd backend
mvn test
```

**Run specific test class**:
```bash
mvn test -Dtest=UserServiceTest
```

**With coverage report**:
```bash
mvn test jacoco:report
```

### Frontend Testing

**Run all tests**:
```bash
cd frontend
npm test
```

**Run tests in headless mode**:
```bash
npm test -- --watch=false --browsers=ChromeHeadless
```

**Generate coverage report**:
```bash
npm test -- --code-coverage
```

---

##  Build & Deployment

### Build Backend (Production)

```bash
cd backend
mvn clean package
```

**JAR file location**: `target/InventoryManagementSystem-0.0.1-SNAPSHOT.jar`

**Run JAR**:
```bash
java -jar target/InventoryManagementSystem-0.0.1-SNAPSHOT.jar
```

### Build Frontend (Production)

```bash
cd frontend
npm run build
```

**Output directory**: `dist/ims-angular/`

**Deploy frontend** (to web server):
```bash
# Copy dist folder to web server (Apache, Nginx, etc.)
cp -r dist/ims-angular/* /var/www/html/
```

---

##  Troubleshooting

### Common Issues

#### 1. Database Connection Error
```
Error: FATAL: role "postgres" does not exist
```
**Solution**: Create user in PostgreSQL
```sql
CREATE ROLE postgres WITH LOGIN PASSWORD '2004';
ALTER ROLE postgres CREATEDB;
```

#### 2. Port Already in Use
```
Error: Bind exception - port 5050/4200 already in use
```
**Solution**: Change port in `application.properties` or `angular.json`, or kill existing process

#### 3. JWT Token Expired
**Solution**: Clear localStorage and log in again
```javascript
localStorage.clear();
```

#### 4. CORS Error
**Solution**: Ensure backend is running on port 5050 and frontend on port 4200

#### 5. Image Not Loading
**Solution**: Check image path in `ims.upload.images-relative` property

---

##  Contributing

### Code Guidelines
- Follow Spring Boot conventions and best practices
- Use meaningful variable and method names
- Add comments for complex logic
- Maintain service layer separation
- Write unit tests for critical functions

### Git Workflow
```bash
1. Create feature branch: git checkout -b feature/feature-name
2. Commit changes: git commit -m "Add feature description"
3. Push to remote: git push origin feature/feature-name
4. Create Pull Request and request review
```

---

##  License

This project is part of the HCL-LMS initiative. Use according to organizational policies.

---

##  Support & Contact

For issues, feature requests, or questions:
- Create an issue in the repository
- Contact the development team
- Check existing documentation in the Wiki

---

##  Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | 2024 | Initial release with core features |

---

**Last Updated**: April 2026  
**Project Lead**: HCL Development Team  
**Status**: Active Development
