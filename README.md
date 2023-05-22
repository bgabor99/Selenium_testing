# Usage
## Commands
```bash
docker compose up
docker exec -it [container name] bash
cd tests
gradle clean && gradle test
```
## Infos
- The noVNC server can be watched on the http://localhost:8081/ url after you started the compose.
- Note that this testing the UI, therefore it can fail in the future if something will change on the tested page.
    - Last successful test run was on 5/22/2023.
