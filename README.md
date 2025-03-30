http://localhost:8081/realms/opensource/.well-known/openid-configuration


curl -X POST http://localhost:8081/realms/opensource/protocol/openid-connect/token \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "grant_type=password&client_id=open-rest-api&username=user1&password=user1"
