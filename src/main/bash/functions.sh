#!/bin/bash

scriptPath=/path/to/your/script
port=serverPort
server=serverAddress

function pingAndStart() {
    echo "ping" | nc $server $port > /tmp/ping

    [[ `cat /tmp/ping` != pong ]] && \
        $scriptPath

    while [[ `cat /tmp/ping` != pong ]]; do
        sleep 0.1
        echo "ping" | nc $server $port > /tmp/ping
    done
}


function execCommand() {
    echo $1 | nc $server $port > /tmp/myScript
    chmod a+x /tmp/myScript
    . /tmp/myScript
}



