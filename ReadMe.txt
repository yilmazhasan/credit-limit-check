To start api:
1. cd .\initial
2. ./gradlew bootRun

To test:
1. Make a request to localhost:8080/credit/check with json a payload
{"fullname":"hy","income":"156","path":"test","tckn":"1","phone":"5556907419"}

On cmd:
- curl --header "Content-Type: application/json" --request POST --data "{\"fullname\":\"hy\",\"income\":\"156\",\"path\":\"test\",\"tckn\":\"3\",\"phone\":\"5556907419\"}"  localhost:8080/credit/check

