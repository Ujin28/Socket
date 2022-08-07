const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index.html');
});

io.on('connection', (socket) => {
    console.log('a user connected');
});

server.listen(3000, () => {
    console.log('listening on *:3000');
});

io.on('connection', (socket) => {
    socket.on('number', (msg) => {
        console.log('message: ' + msg);
        parseInt(msg);
        if (msg>0) {socket.emit("result", "양수");
            console.log('양수');}
        if (msg<0) {io.emit("result", "음수");
            console.log('음수');}
        if (msg == 0) {io.emit("result", "0");
            console.log('0');}
    });
});