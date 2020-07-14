#include "SocketTCP.hpp"

int main (int argc, char* argv[]) {

     if(argc!=3) {
         printf("USAGE: %s PORT MSG\n", argv[0]);
         return -1;
	}

    int port = atoi(argv[1]);
    char* answer = argv[2];

    TCP_Server myself = TCP_Server("127.0.0.1", port); 
    Server_Connection* conn = myself.accetta();
   
    char* request = conn->ricevi(); 
    Address client = conn->get_address();

    char* client_ip = client.get_ip(); 
    int client_port = client.get_port();

    printf("[%s:%d] %s\n", client_ip, client_port, request); 

    free(client_ip);
  
    conn->invia(answer);

    return 0;
   } 
