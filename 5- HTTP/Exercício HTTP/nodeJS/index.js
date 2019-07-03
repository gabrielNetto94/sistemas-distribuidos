
const http = require('http')
const express = require('express');
var bodyParser = require('body-parser')
var path = require('path');
const app = express();
const server = require('http').Server(app);

app.use(bodyParser.json());

app.use(bodyParser.urlencoded({ extended: true }));


var auth = ['adminsitrador', '12346'];

//qaunto tiver req no "/", returna um index.html
app.get('/', (req, res) => {
    console.log('GET\nNome: ' + req.query.name + ' \nPassword: ' + req.query.password);
    res.sendFile(path.join(__dirname + '/index.html'));
    //return res.json(req.query.name+'asdas'+req.query.password);
    
});

app.get('/login', (req, res) => {    
    if (req.query.name === 'a' && req.query.password === 'a') {
        res.sendFile(path.join(__dirname + '/welcome.html'));
    } else {
        res.sendFile(path.join(__dirname + '/index.html'));
    }

});


app.post('/login', (req, res) => {
    console.log('gugu > POST\nNome: ' + req.body.login + ' \nPassword: ' +  req.body.password);
    res.sendFile(path.join(__dirname + '/welcome.html'),{ login: 'tobi' });
    
});


server.listen(3000);
console.log('Rodando na porta 3000');
