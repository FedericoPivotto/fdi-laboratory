/*
	Tipo di dato astratto Stack
*/
interface Stack extends Container
{
	/*
		Inserimento in cima alla pila dell'oggetto
	*/
	void push(Object obj);

	/*
		Rimozione in cima alla pila e restituzione dell'oggetto
	*/
	Object pop();

	/*
		Ispezionamento dell'oggetto in cima alla pila
	*/
	Object top();
}