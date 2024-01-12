#!/bin/bash

echo "waiting 20s for the SQL Server to come up..."
sleep 20s

echo "running set up script..."
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P SomeStrongPwd123 -d master -i db-init.sql