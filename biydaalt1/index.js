let isOpen = false;

function toggleChat() {
	const chatWidget = document.getElementById('chat-widget');
	isOpen = !isOpen;
	chatWidget.style.height = isOpen ? '450px' : '60px';
	chatWidget.style.borderRadius = isOpen ? '5px' : '100%';
	chatWidget.style.width = isOpen ? '300px' : '60px';
	const circleImage = document.getElementById('circle').getElementsByTagName('img')[0];
	circleImage.style.display = isOpen ? 'none' : 'flex';
	updateToggleButton();
}   

function updateToggleButton() {
	const toggleButton = document.getElementById('toggleButton');
	toggleButton.textContent = isOpen ? 'Эрка-тай чатлах' : '';
	toggleButton.height = isOpen ? '30px' : '0px';
	toggleButton.style.display = isOpen ? 'block' : 'none';
}


async function sendMessage() {
	const userInput = document.getElementById('userInput').value;
	document.getElementById('chatbox').innerHTML += `<p>User: ${userInput}</p>`;

	const response = await fetch('http://localhost:5005/webhooks/rest/webhook', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ message: userInput }),
	});

	const responseData = await response.json();
	const botResponse = responseData[0].text;

	document.getElementById('chatbox').innerHTML += `<p>Bot: ${botResponse}</p>`;
	document.getElementById('userInput').value = '';
	scrollToBottom();
}

function handleKeyPress(event) {
	if (event.key === 'Enter') {
		sendMessage();
	}
}

function scrollToBottom() {
	const chatbox = document.getElementById('chatbox');
	chatbox.scrollTop = chatbox.scrollHeight;
}

document.addEventListener('DOMContentLoaded', () => {

	const sendButton = document.getElementById('sendButton');
	if (sendButton) {
	  sendButton.textContent = 'Эрка-руу чат явуулах';
	}
  
	const messageInput = document.getElementById('userInput');
	if (messageInput) {
	  messageInput.placeholder = 'aliv manitaiga heden ym yrilda';
	}
  });
  

const hamburger = document.querySelector('.header .nav-bar .nav-list .hamburger');
const mobile_menu = document.querySelector('.header .nav-bar .nav-list ul');
const menu_item = document.querySelectorAll('.header .nav-bar .nav-list ul li a');
const header = document.querySelector('.header.container');

hamburger.addEventListener('click', () => {
	hamburger.classList.toggle('active');
	mobile_menu.classList.toggle('active');
});

document.addEventListener('scroll', () => {
	var scroll_position = window.scrollY;
	if (scroll_position > 250) {
		header.style.backgroundColor = '#29323c';
	} else {
		header.style.backgroundColor = 'transparent';
	}
});

menu_item.forEach((item) => {
	item.addEventListener('click', () => {
		hamburger.classList.toggle('active');
		mobile_menu.classList.toggle('active');
	});
});