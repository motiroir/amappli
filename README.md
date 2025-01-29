# Amappli - SaaS for AMAPs

## Introduction
Amappli is a SaaS platform designed for **AMAPs (Associations for the Maintenance of Small-Scale Farming)**. Its mission is to create a connected network of AMAPs across France, providing greater visibility and making it easier for potential members to find and join local AMAPs.

The platform enables AMAPs to **effortlessly create and manage their own websites**, offering a suite of digital tools tailored to their needs. This includes a customizable e-commerce solution, membership management, and role-based access control for delegation.

## Key Features
- **Subscription-based SaaS Model** with different plans offering various features.
- **E-commerce & Order Management**: Sell basket subscriptions, grocery products, and workshops.
- **User & Supplier Management**: Handle members, suppliers, and product listings.
- **Role-Based Access Control**: Assign specific roles to administrators and volunteers.
- **Customizable Website**: Modify design elements, content, and branding.
- **Dark/Light Mode Toggle** for enhanced user experience.
- **Dynamic Topographic Illustrations**: Each AMAP's site features **unique terrain-based designs** generated from its geographical location via Mapbox API.

## Tech Stack & Architecture
- **Backend**: Java EE with **Spring MVC**, **Spring Security 6**, and **Spring Data JPA**.
- **Database**: MySQL with a **shared pool model** for efficiency.
- **Frontend**: JSP with **Bootstrap 5** for responsive design.
- **Security**: Spring Security **handles authentication and role-based access**.
- **Hosting**: Deployed on **Tomcat server**.
- **Image Storage**: User-generated images stored in MySQL for fast access.

### Code Structure
- **MVC Architecture** (Model-View-Controller) for **clear separation of concerns**.
- **Service Layer**: Business logic and data processing.
- **Repository Layer**: Database interactions with Hibernate.
- **Builder Pattern**: Used for managing complex object creation.

## Why SaaS for AMAPs?
- **No installation required**: Access from any device.
- **Automatic updates**: Ensuring smooth maintenance and new feature rollouts.
- **Cost-effective**: Shared infrastructure reduces overhead.
- **Scalability**: Designed to grow with the AMAP ecosystem.

## Get Started
1. **Sign up** for an account.
2. **Create your AMAP’s website** in just a few clicks.
3. **Customize** with your branding and features.
4. **Start managing your members, orders, and content** seamlessly!

---

🚀 Built with passion to support local agriculture and sustainable food networks! 🌱

