#ifndef __SOCKETUDP_HPP
#define __SOCKETUDP_HPP
#include "Address.hpp"
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <cstdlib>
#include <cstdio>

#ifndef DEFAULT_IP
#define DEFAULT_IP "0.0.0.0" //oppure 127.0.0.1
#endif // DEFAULT_IP
#ifndef MAX_STR
#define MAX_STR 1024
#endif // MAX_STR

class SocketUDP
{
private:
    int id;    
public:
    // costruttori
    SocketUDP(); // client
    SocketUDP(int); // server
    SocketUDP(char* , int);
    SocketUDP(Address);

    // functions
    bool send(char*, Address);
    bool send(char*, int, Address);
    char* receive(Address*);
    int receive(Address*, char*, int);
    bool broadcast(bool);
    void errore(char*, int);

    ~SocketUDP(); // distruttore
};

// costruttore senza parametri
SocketUDP::SocketUDP(){
	this->id = socket(AF_INET, SOCK_DGRAM, 0);
	//if (socketId== -1) errore("socket()", -2);
}

// costruttore che riceve la porta
SocketUDP::SocketUDP(int port) {
	this->id = socket(AF_INET, SOCK_DGRAM, 0);
	//if (socketId== -1) errore("socket()", -2);
		
	Address* address = new Address(DEFAULT_IP, port);	
	
	struct sockaddr_in addr = address->getAddr();
	
	int ret = bind(this->id,
                   (struct sockaddr*) &addr,
                   (socklen_t)sizeof(struct sockaddr_in));
	//if (ret) errore("bind()", -3);		
}

// costruttore che riceve l'indirizzo e la porta
SocketUDP::SocketUDP(char* addr, int port) {
	this->id = socket(AF_INET, SOCK_DGRAM, 0);
	//if (socketId== -1) errore("socket()", -2);
		
	Address* address = new Address(addr, port);	
	
	struct sockaddr_in structAddr = address->getAddr();
	
	int ret = bind(this->id,
                   (struct sockaddr*) &structAddr,
                   (socklen_t)sizeof(struct sockaddr_in));
	//if (ret) errore("bind()", -3);
} 

// costruttore che riceve un'istanza di classe Address
SocketUDP::SocketUDP(Address indirizzo) {
	this->id = socket(AF_INET, SOCK_DGRAM, 0);
	//if (socketId== -1) errore("socket()", -2);
			
	struct sockaddr_in structAddr = indirizzo.getAddr();
	
	int ret = bind(this->id,
                   (struct sockaddr*) &structAddr,
                   (socklen_t)sizeof(struct sockaddr_in));
	//if (ret) errore("bind()", -3);
} 

// metodo post che riceve una stringa e un'istanza della classe Address(destinatario)
bool SocketUDP::send(char* msg, Address dest) {
	struct sockaddr_in structAddr = dest.getAddr();
	
	int ret = sendto(this->id,
				 msg,
				 strlen(msg)+1,
				 0,
				 (struct sockaddr*)&structAddr,
				 (socklen_t)sizeof(struct sockaddr_in));
	if(ret<=0) return false;
	else return true;
}
// metodo post che riceve una stringa, un intero che rappresenta il numero di caratteri da trasmettere e un'istanza della classe Address(destinatario)
bool SocketUDP::send(char* msg, int leng, Address dest) {
	struct sockaddr_in structAddr = dest.getAddr();
	
	int ret = sendto(this->id,
				 msg,
				 leng,
				 0,
				 (struct sockaddr*)&structAddr,
				 (socklen_t)sizeof(struct sockaddr_in));
	if(ret<=0) return false;
	else return true;
}

// metodo receive che riceve un'istanza di classe Address per referenza che rappresenta il mittente
char* SocketUDP::receive(Address *mitt){
	char buffer[MAX_STR+1];
	struct sockaddr_in sender;
	int len=sizeof(struct sockaddr_in);

	int ret = recvfrom (this->id,
                    buffer,
                    MAX_STR,
                    0,
                    (struct sockaddr*)&sender,
                    (socklen_t*)&len);
	//if(ret<=0) errore("recvfrom()", -9);
	buffer[ret] = '\0';

	mitt->setAddr((struct sockaddr_in)sender);
	return strdup(buffer);
}

// funzione receive che riceve un'istanza della classe Address per referenza(mittente), un buffer e un intero che rappresenta la lunghezza del buffer
int SocketUDP::receive(Address* mitt, char* buffer, int leng) {
	struct sockaddr_in sender;
	int len=sizeof(struct sockaddr_in);

	int ret = recvfrom (this->id,
                    buffer,
                    leng,
                    0,
                    (struct sockaddr*)&sender,
                    (socklen_t*)&len);
	//if(ret<=0) errore("recvfrom()", -9);
	buffer[ret] = '\0';

	mitt->setAddr((struct sockaddr_in)sender);
	return ret;
}

// funzione broadcast che riceve un boolean(enable)
bool SocketUDP::broadcast(bool enable){
	if(enable){
		char opt_value ='1';
		if(setsockopt(this->id, SOL_SOCKET, SO_BROADCAST, &opt_value, sizeof(opt_value)))
			return false;
		else 
		 return true;
	} else return false;
}

SocketUDP::~SocketUDP() {
	delete this;	
}    

#endif //__SOCKETUDP_HPP



