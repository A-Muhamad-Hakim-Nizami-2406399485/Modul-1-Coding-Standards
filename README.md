Name : Muhamad Hakim Nizami

Class : A

NPM : 2406399485

# Reflection 1

I reviewed the Spring Boot code I implemented and evaluated it against the clean code
principles and secure coding practices taught in this module. Below I list what is
already applied, what needs improvement, and concrete suggestions to fix issues.

- Quick change: added this reflection after re-checking the code in the following files:
  - `src/main/java/id/ac/ui/cs/advprog/eshop/controller/ProductController.java`
  - `src/main/java/id/ac/ui/cs/advprog/eshop/service/ProductServiceImpl.java`
  - `src/main/java/id/ac/ui/cs/advprog/eshop/repository/ProductRepository.java`
  - `src/main/java/id/ac/ui/cs/advprog/eshop/model/Product.java`

- Applied clean code principles:
  - Single Responsibility: controller, service and repository (in the MVC manner) are split into separate classes
    (`ProductController`, `ProductServiceImpl`, `ProductRepository`).
  - Meaningful names: classes and methods (e.g. `create`, `findAll`) convey intent.
  - Small, focused methods: controller methods are small and delegate to service layer.
  - Interface usage: `ProductService` provides a contract separate from the implementation.

- Secure coding practices present:
  - No secrets or credentials are hard-coded in the repository.
  - Using template rendering (Thymeleaf) reduces direct string concatenation in views,
    which helps avoid some injection classes when templates are used correctly.