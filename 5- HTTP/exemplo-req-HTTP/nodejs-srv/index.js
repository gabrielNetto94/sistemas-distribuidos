
const http = require('http')
const express = require('express');
const app = express();
const server = require('http').Server(app);

app.get('/',(req, res) =>{

  console.log('GET\nNome: '+req.query.name+' \nPassword: '+req.query.password);
  //return res.end('teste');
  return res.json(req.query.name+req.query.password);

});

app.post('/',(req, res) =>{

  console.log('POST\nNome: '+req.query.name+' \nPassword: '+req.query.password);
  //return res.end('teste');
  
  
});

server.listen(3000);

/**
  const server = http.createServer(function(req, res){
      res.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'});
      if(req.url == "/"){
            res.write("<h1>Ola Node.js</h1>");
      }else if(req.url == "/outra/"){
            res.write("<h1>Outra página</h1>");
      }else{
            res.writeHead(404, {'Content-Type': 'text/html; charset=utf-8'});
            res.write("<h1>Página não encontrada</h1>");
      }
      res.end();
  });
  
*/



