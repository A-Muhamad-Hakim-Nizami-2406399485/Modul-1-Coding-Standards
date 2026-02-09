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

# Reflection 2

1) After writing unit tests: feelings, quantity, and code coverage

- Writing unit tests made me more confident about small, isolated behaviors (e.g., repository CRUD and service delegation). Seeing tests fail quickly when I changed signatures reminded me that good unit tests serve as a fast feedback loop.
- How many unit tests per class? It depends on behavior, not lines of code. A reasonable approach is one test per distinct behavior/branch, plus edge cases. For example, in ProductRepository: create, findAll (empty/multiple), findById (present/missing), update (present/missing), and delete (present/missing) are distinct behaviors that each deserve tests.
- Code coverage helps identify untested lines/branches, but 100% coverage does not guarantee no bugs. Coverage only shows that code was executed, not that assertions were meaningful. High-quality assertions, testing observable behavior (not implementation details), and covering edge cases matter more than chasing 100%.

2) Clean code reflection for a new functional test suite (verifying product list count)

- Potential issues if I duplicate prior functional test setup verbatim:
  - Duplication of setup logic
  - Magic values in assertions (expected titles/messages) scattered across tests → harder to update when UI changes.
  - Inconsistent naming and responsibilities
- Why this reduces code quality:
  - Duplication inflates the surface area for bugs when environment changes (ports, base URL).
  - Tight coupling to view details makes refactoring the UI risky.
  - Unclear responsibilities hinder readability and future additions.
- Suggested improvements:
  - Introduce a small functional test base class for common setup (computing baseUrl from @LocalServerPort and @Value app.baseUrl) and shared helpers.
  - Use stable selectors instead of generic tags for locating elements, especially for counting items.
  - Centralize shared constants (expected title, welcome text) in one place to avoid magic values.
  - Group tests by scenario: HomePageTests (title, welcome), ProductListTests (navigation, count), keeping each class focused.
  - Prefer minimal fixtures: seed only what the test needs (e.g., add two products via controller/service or a test-only seeding endpoint) to assert list counts deterministically.
  - Consider parameterized tests for similar assertions (e.g., various product counts) to reduce duplication.

