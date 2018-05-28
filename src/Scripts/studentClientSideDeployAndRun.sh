echo "Compressing data to be sent to the student client side node."
rm -rf studentClientSide.zip
zip -rq studentClientSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs 
echo "Transfering data to the client side node."
sshpass -f password ssh cd0208@l040101-ws06.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws06.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp studentClientSide.zip cd0208@l040101-ws06.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the client side node."
sshpass -f password ssh cd0208@l040101-ws06.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq studentClientSide.zip'
echo "Compiling program files at the student client side node."
sshpass -f password ssh cd0208@l040101-ws06.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
sleep 3
echo "Executing student program at the client side node."
sshpass -f password ssh cd0208@l040101-ws06.ua.pt 'cd CDSockets/RestauranteSocket ; java student.StudentMain'
