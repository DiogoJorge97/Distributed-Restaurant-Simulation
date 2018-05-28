echo "Compressing data to be sent to the kitchen server side node."
rm -rf kitchenServerSide.zip
zip -rq kitchenServerSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs  waiter ClientSideGeral
echo "Transfering data to the server side node."
sshpass -f password ssh cd0208@l040101-ws02.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws02.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp kitchenServerSide.zip cd0208@l040101-ws02.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the server side node."
sshpass -f password ssh cd0208@l040101-ws02.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq kitchenServerSide.zip'
echo "Compiling program files at the server side node."
sshpass -f password ssh cd0208@l040101-ws02.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
echo "End of compiling at the kitchen server side node."
sleep 1
echo "Executing kitchen program at the server side node."
sshpass -f password ssh cd0208@l040101-ws02.ua.pt 'cd CDSockets/RestauranteSocket ; java kitchen.KitchenMain'
echo "Server  shutdown."

