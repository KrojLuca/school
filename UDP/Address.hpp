#ifndef _ADDRESS_HPP
#define _ADDRESS_HPP

#include <sys/socket.h>
#include <arpa/inet.h>

#define DEFAULT_IP "0.0.0.0"
#define DEFAULT_PORT 12000
#define TOSTRING_MAX_LENGTH 22

public class Address{
private:
struct sockaddr_in _address;
public:
Address();
Address(Address& sourceObject);
Address(struct sockaddr_in address);
Address(char* ip, int port);

~Address();

struct sockaddr_in get_address();
void set_address(struct sockaddr_in address);
char *get_ip();
void set_ip(char *ip);
int get_port();
void set_port(int port);

char *toString();
};

Address::Address() : Address(DEFAULT_IP, DEFAULT_PORT) {}
Address::Address(Address& sourceObject){
this->_address = sourceObject._address;
}
Address::Address(struct sockaddr_in address){
_address = address;
}
Address::Address(char* ip, int port){
_address.sin_family = AF_INET;
inet_aton(ip, &_address.sin_addr);
_address.sin_port = htons(port);
for (int i = 0; i < 8; ++i)
_address.sin_zero[i]=0;
}
Address::~Address(){}

struct sockaddr_in Address::get_address(){
return _address;
}
void Address::set_address(struct sockaddr_in address){
this->_address = address;
}

// Questo metodo restituisce una stringa allocata in modo dinamico
// che dovrà essere liberata con free()
char *Address::get_ip(){
return strdup(inet_ntoa(_address.sin_addr));
}

void Address::set_ip(char *ip){
inet_aton(ip, &_address.sin_addr);
}

int Address::get_port() {
return ntohs(_address.sin_port);
}
void Address::set_port(int port){
_address.sin_port = htons(port);
}

// Questo metodo restituisce una stringa allocata in modo dinamico
// che dovrà essere liberata con free()
char *Address::toString(){
char str[TOSTRING_MAX_LENGTH];
sprintf(str, "%s:%d",
inet_ntoa(_address.sin_addr), ntohs(_address.sin_port));
return strdup(str);
}

#endif // _ADDRESS_HPP