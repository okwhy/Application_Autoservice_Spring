#!/bin/bash
/db-init.sh & /opt/mssql/bin/sqlservr
echo "initialization finished"