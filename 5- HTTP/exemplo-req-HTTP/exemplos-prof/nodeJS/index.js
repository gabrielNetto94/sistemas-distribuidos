
const http = require('http')
const express = require('express');
var path = require('path');
const app = express();
const server = require('http').Server(app);

var auth = ['adminsitrador', '12346'];

//qaunto tiver req no "/", returna um index.html
app.get('/', (req, res) => {
    console.log('GET\nNome: ' + req.query.name + ' \nPassword: ' + req.query.password);
    res.sendFile(path.join(__dirname + '/index.html'));
    //return res.json(req.query.name+'asdas'+req.query.password);
});

app.get('/login', (req, res) => {

    // auth.find(req.query.name);
    // auth.map('123456');

    if (req.query.name === 'a' && req.query.password === 'a') {
        res.sendFile(path.join(__dirname + '/welcome.html'));
    } else {
        res.sendFile(path.join(__dirname + '/index.html'));
    }

});

app.post('/', (req, res) => {
    console.log('POST\nNome: ' + req.query.name + ' \nPassword: ' +  req.query.password);
    //return res.end('teste');
});


server.listen(3000);
console.log('Rodando na porta 3000');
