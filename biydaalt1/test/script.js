document.addEventListener('DOMContentLoaded', function () {
    var chatWidget = document.getElementById('chat-widget');
    var toggleChatButton = document.getElementById('toggle-chat');
    var messageInput = document.getElementById('message-input');
    var sendMessageButton = document.getElementById('send-message');
    var chatBody = document.getElementById('chat-body');

    toggleChatButton.addEventListener('click', function () {
        chatWidget.classList.toggle('closed');
    });

    sendMessageButton.addEventListener('click', function () {
        var messageText = messageInput.value.trim();
        if (messageText !== '') {
            var messageElement = document.createElement('div');
            messageElement.classList.add('message');
            messageElement.textContent = messageText;
            chatBody.appendChild(messageElement);
            messageInput.value = '';
        }
    });
});
