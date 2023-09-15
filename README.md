## Account Validator

```bash
API Endpoint
POST: http://localhost:8081/validate/account
```
```bash
Payload:
{
  "accountNumber": "accountNumber",
  "providers": [
    "provider1",
    "provider2"
  ]
}
```

```bash
Response
{
  "result": [
    {
      "provider": "provider1",
      "valid": false
    },
    {
      "provider": "provider2",
      "valid": false
    }
  ]
}
```

#### Curl
```bash
curl --location 'localhost:8081/validate/account' \
--header 'Content-Type: application/json' \
--data '{
   "accountNumber":"neeraj",
   "providers": ["provider1", "provider2"]
}'
```

