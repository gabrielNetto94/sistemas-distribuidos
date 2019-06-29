
#instalar o modulo requests:
#$python -m pip install requests

import requests
print(requests.post("http://localhost/exemplos/servidor.php",data={'parametro1':'3', 'parametro2':'2'}).content)
print(requests.post("http://localhost:8000/exemplo",data={'parametro1':'30', 'parametro2':'20'}).content)