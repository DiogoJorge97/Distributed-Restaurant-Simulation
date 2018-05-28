echo "Compressing data to be sent to the table server side node."
rm -rf tableServerSide.zip
zip -rq tableServerSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs 
echo "Transfering data to the server side node."
sshpass -f password ssh cd0208@l040101-ws04.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws04.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp tableServerSide.zip cd0208@l040101-ws04.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the server side node."
sshpass -f password ssh cd0208@l040101-ws04.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq tableServerSide.zip'
echo "Compiling program files at the server side node."
sshpass -f password ssh cd0208@l040101-ws04.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
echo "End of compiling at the table server side node."
sleep 1
echo "Executing table program at the server side node."
sshpass -f password ssh cd0208@l040101-ws04.ua.pt 'cd CDSockets/RestauranteSocket ; java table.TableMain'
echo "Server  shutdown."

