var http = require('http')

var server = http.createServer(function(req,res){
    res.writeHead(200,{
        'Content-type':'plain/text'
    })
    res.end('Ciao belli')
    })
    server.listen(80)
    console.log('Server running')
