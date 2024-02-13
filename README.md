# Spring Data quiz
GB Java Spring S05 APIs  

## 1. Normal task (Task part 01)
Вам предстоит создать приложение для управления списком задач с использованием Spring Boot и Spring Data JPA. Требуется реализовать следующие функции:
* Добавление задачи. Подсказка метод в контроллере: `@PostMapping public Task addTask(@RequestBody Task task)`
* Просмотр всех задач. Подсказка метод в контроллере: `@GetMapping public List<Task> getAllTasks()`
* Просмотр задач по статусу (например, "завершена", "в процессе", "не начата"). Подсказка метод в контроллере: `@GetMapping("/status/{status}") public List<Task> getTasksByStatus(@PathVariable TaskStatus status)`
* Изменение статуса задачи. Подсказка метод в контроллере: `@PutMapping("/{id}") public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)`
* Удаление задачи.  

Подсказка метод в контроллере:
```java
@DeleteMapping("/{id}") 
public void deleteTask(@PathVariable Long id);
```
Репозиторий подсказка 
```java
public interface TaskRepository extends JpaRepository<Task, Long>{};
```   
Структура задачи(класс Task):
- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)
  Подсказка понадобится энумератор:
  enum TaskStatus {
  NOT_STARTED, IN_PROGRESS, COMPLETED;
  }  


## 2. Extra tasks (Task part 02)

Текст сложного задания на тему Spring Data JPA:

Ваша команда разрабатывает приложение для управления проектами. Вам нужно реализовать функциональность связывания пользователей с проектами.

Создайте сущность "пользователь" (User), которая будет содержать следующие поля:

- Идентификатор (id) типа Long, генерируемый автоматически при создании записи  
- Имя пользователя (username) типа String  
- Пароль (password) типа String  
- Электронная почта (email) типа String  
- Роль (role) типа String  

Создайте сущность "проект" (Project), которая будет содержать следующие поля:

- Идентификатор (id) типа Long, генерируемый автоматически при создании записи
- Название проекта (name) типа String
- Описание проекта (description) типа String
- Дата создания (createdDate) типа LocalDate  

Создайте абстрактный класс "сущность с связью" (EntityWithRelation), который будет содержать следующие поля:

Идентификатор (id) типа Long, генерируемый автоматически при создании записи
Идентификатор связанной сущности (relatedEntityId) типа Long
Создайте сущность "пользователи проекта" (UsersProject), которая наследуется от класса "сущность с связью" и будет содержать следующие поля:

Идентификатор проекта (projectId) типа Long
Идентификатор пользователя (userId) типа Long
Создайте интерфейс репозитория (UserRepository), который будет расширять JpaRepository<User, Long>.

Создайте интерфейс репозитория (ProjectRepository), который будет расширять JpaRepository<Project, Long>.

Создайте интерфейс репозитория (UsersProjectRepository), который будет расширять JpaRepository<UsersProject, Long>.

Создайте сервисный класс (UserProjectService), который будет содержать следующие методы:

public List getUsersByProjectId(Long projectId) - метод, возвращающий список пользователей, связанных с определенным проектом
public List getProjectsByUserId(Long userId) - метод, возвращающий список проектов, связанных с определенным пользователем
public void addUserToProject(Long userId, Long projectId) - метод, добавляющий пользователя к проекту
public void removeUserFromProject(Long userId, Long projectId) - метод, удаляющий пользователя из проекта
Создайте контроллер (UserProjectController), который будет содержать следующие методы:

public ResponseEntity<List> getUsersByProjectId(Long projectId) - метод, обрабатывающий GET-запрос для получения списка пользователей, связанных с определенным проектом
public ResponseEntity<List> getProjectsByUserId(Long userId) - метод, обрабатывающий GET-запрос для получения списка проектов, связанных с определенным пользователем
public ResponseEntity addUserToProject(Long userId, Long projectId) - метод, обрабатывающий POST-запрос для добавления пользователя к проекту
public ResponseEntity removeUserFromProject(Long userId, Long projectId) - метод, обрабатывающий POST-запрос для удаления пользователя из проекта



