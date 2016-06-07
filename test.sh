#!/bin/bash

echo "echo $PATH" | nc localhost 3000 > /tmp/myScript
chmod a+x /tmp/myScript
/tmp/myScript
