 PayPal Clone — Microservices Architecture
A full-featured PayPal-inspired payment platform built with Java, Spring Boot, and a microservices architecture. Supports user management, digital wallets, transactions, reward points, and real-time notifications.


🧩 Microservices
1. 👤 user-service — User Management
Handles registration, authentication, KYC verification, and profile management.
EndpointMethodDescription/api/users/registerPOSTRegister a new user/api/users/loginPOSTAuthenticate & receive JWT/api/users/{id}GETGet user profile/api/users/{id}PUTUpdate profile/api/users/{id}/verifyPOSTKYC / email verification
Key Features:

JWT-based authentication with Spring Security
BCrypt password hashing
Role-based access control (USER, ADMIN)
Email verification flow


2. 💰 wallet-service — Digital Wallet
Manages user balances, funding sources, and withdrawal requests.
EndpointMethodDescription/api/wallets/{userId}GETGet wallet balance/api/wallets/{userId}/depositPOSTAdd funds/api/wallets/{userId}/withdrawPOSTWithdraw funds/api/wallets/{userId}/historyGETBalance history
Key Features:

Multi-currency support (USD, EUR, GBP, INR)
Optimistic locking to prevent race conditions
Redis caching for balance lookups
Linked bank account / card management


3. 💸 Transaction — Payment Processing
Handles P2P transfers, payment requests, refunds, and transaction history.
EndpointMethodDescription/api/transactions/sendPOSTSend money to another user/api/transactions/requestPOSTRequest money from a user/api/transactions/{id}GETGet transaction details/api/transactions/historyGETPaginated transaction history/api/transactions/{id}/refundPOSTInitiate a refund
Key Features:

ACID-compliant transfers with distributed transactions
Transaction status lifecycle: PENDING → PROCESSING → COMPLETED / FAILED
Kafka event publishing on every state change
Idempotency key support to prevent duplicate payments


4. 🔔 notification-service — Alerts & Messaging
Listens to Kafka events and delivers real-time notifications across channels.
EndpointMethodDescription/api/notifications/{userId}GETGet user notifications/api/notifications/{id}/readPUTMark as read/api/notifications/preferencesPUTUpdate alert settings
Key Features:

Kafka consumer for transaction and reward events
Email notifications via Spring Mail (SMTP)
In-app notification storage
Push notification support (FCM)


5. 🏆 reward-service — Loyalty & Cashback
Tracks reward points earned from transactions and handles redemptions.
EndpointMethodDescription/api/rewards/{userId}GETGet reward balance & tier/api/rewards/{userId}/historyGETPoints history/api/rewards/redeemPOSTRedeem points for cashback/api/rewards/tiersGETGet all reward tier rules
Key Features:

Points awarded automatically on transaction completion (via Kafka)
Tiered rewards: Bronze → Silver → Gold → Platinum
Expiry rules for points
Cashback redemption applied to wallet balance


🛠️ Tech Stack
LayerTechnologyLanguageJava 17FrameworkSpring Boot 3.xSecuritySpring Security + JWTORMSpring Data JPA / HibernateDatabaseMySQL 8 (per service)CacheRedisMessagingApache KafkaService DiscoverySpring Cloud EurekaAPI GatewaySpring Cloud GatewayContainerizationDocker + Docker ComposeBuild ToolMavenDocumentationSwagger / OpenAPI 3
