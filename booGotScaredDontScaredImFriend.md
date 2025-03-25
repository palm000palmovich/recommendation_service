2-е модели, которые участвуют в работе  с динамическими рекоммендациями:
1) модель **RecommendationsByRules**:
   `private UUID product_id;
   private String product_name;
   private String product_text;`

2) модель **Rule**:
   `private Long id;
   private String query;
   private List<String> arguments;
   private boolean negate;`


Так как на одну рекоммендацию приходится несколько правил, я установил 
связи между 2-мя таблица:
-`@OneToMany(mappedBy = "recommendation")
private List<Rule> rule;`    ВНУТРИ МОДЕЛИ **RecommendationsByRules**

-`@ManyToOne
@JoinColumn(name = "product_id")
private RecommendationsByRules recommendation;`  ВНУТРИ МОДЕЛИ **Rules**

!!!моедель **Rules** является одним из полей модели **RecommendationsByRules**

Для каждой модели был создан соответствующий JPA-репозиторий в пакете
repositories

через класс `RecsByRulesService` в методе
`saveNewRecommendation` происходит получение и обработка через
post-запрос объекта класса `RecommendationsByRules`

Также в этом методе сервиса я добавил логгирование на отслеживание
корректности полученных post-данных, успешности сохранения данных в обе
таблицы

для проверки работы в SwaggerUI я просто отправляю в теле post-запроса этот 
json:

`{
"product_name": "string",
"product_text": "string",
"rule": [
    {
"query": "string",
"arguments": [
"string"
 ],
"negate": true
    }
  ]
}`


!!!Какие есть типы динамических правил

1) `{
   "query": "USER_OF",
   "arguments": [
   "CREDIT"
   ],
   "negate": true
   }`

USER_OF - проверка, явлется ли юзер пользователем продукта CREDIT
"negate": true - означает, что **НЕТ**

2) `{
   "query": "ACTIVE_USER_OF",
   "arguments": [
   "DEBIT"
   ],
   "negate": false
   }`
ACTIVE_USER_OF - явлется ли юзер **активным**(>= 5 транзакций) 
пользователем продукта DEBIT
"negate": false - означает, что **да**


3) `{
   "query": "TRANSACTION_SUM_COMPARE",
   "arguments": [
   "DEBIT",    //тип продукта
   "DEPOSIT",  //тип транзакции
   ">",
   "100000"
   ],
   "negate": false
   }`
   TRANSACTION_SUM_COMPARE - сравнение стоимости продукта 
DEBIT по DEPOSIT-транзакции со 100к

4) `{
   "query": "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW",
   "arguments": [
   "DEBIT",
   ">"
   ],
   "negate": false
   }`
сравнение 2-х разных транзакций по продукту DEBIT