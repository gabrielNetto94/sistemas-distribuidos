const http = require('http') //requisição via http
const express = require('express');//usado para criar servidor
var path = require('path');//para trabalhar com diretórios
const app = express(); //instância o servidor express
const myParser = require("body-parser"); //parsear mesagens via POST
const server = require('http').Server(app);

//objeto admin
const admin = {
    user:"root",
    password:"root"
};

//quando tiver requisição no "/", retorna um index.html de login
app.get('/', (req, res) => {
    console.log('GET\nNome: ' + req.query.name + ' \nPassword: ' + req.query.password);
    res.sendFile(path.join(__dirname + '/index.html'));
    //return res.json(req.query.name+'asdas'+req.query.password);
    
});

//decodificar os dados da requisição via POST
app.use(myParser.urlencoded({extended : true}));
//trata requisição POST na url /login
app.post("/login", (req, res) => {
    console.log(req.body.login);
    console.log(req.body.password);
      if(req.body.login == admin.user && req.body.password == admin.password){
        res.sendFile(path.join(__dirname + '/welcome.html'));  
      }else{
        res.sendFile(path.join(__dirname + '/index.html'));  
      }
      
});

//trata requisição via GET na url /login
app.get('/login', (req, res) => {

    if (req.query.login === admin.user && req.query.password === admin.password) {
        res.sendFile(path.join(__dirname + '/welcome.html'));
    } else {
        res.sendFile(path.join(__dirname + '/index.html'));
    }

});

server.listen(3000);
console.log('Rodando na porta 3000');