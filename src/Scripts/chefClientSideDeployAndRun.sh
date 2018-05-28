echo "Compressing data to be sent to the chef client side node."
rm -rf chefClientSide.zip
zip -rq chefClientSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs 
echo "Transfering data to the client side node."
sshpass -f password ssh cd0208@l040101-ws05.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws05.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp chefClientSide.zip cd0208@l040101-ws05.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the client side node."
sshpass -f password ssh cd0208@l040101-ws05.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq chefClientSide.zip'
echo "Compiling program files at the chef client side node."
sshpass -f password ssh cd0208@l040101-ws05.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
sleep 3
echo "Executing program at the client side node."
sshpass -f password ssh cd0208@l040101-ws05.ua.pt 'cd CDSockets/RestauranteSocket ; java chef.ChefMain'
