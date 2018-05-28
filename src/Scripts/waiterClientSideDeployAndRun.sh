echo "Compressing data to be sent to the waiter client side node."
rm -rf waiterClientSide.zip
zip -rq waiterClientSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs 
echo "Transfering data to the client side node."
sshpass -f password ssh cd0208@l040101-ws07.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws07.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp waiterClientSide.zip cd0208@l040101-ws07.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the client side node."
sshpass -f password ssh cd0208@l040101-ws07.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq waiterClientSide.zip'
echo "Compiling program files at the waiter client side node."
sshpass -f password ssh cd0208@l040101-ws07.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
sleep 3
echo "Executing waiter program at the client side node."
sshpass -f password ssh cd0208@l040101-ws07.ua.pt 'cd CDSockets/RestauranteSocket ; java waiter.WaiterMain'
