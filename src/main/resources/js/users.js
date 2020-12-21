const getUsersUrl="http://localhost:9090/users";

new Vue({
    el:'#usersList',
    data() {
        return {
            users: []
        }
    },
    created() {
        fetch(getUsersUrl)
            .then(response => {
                return response.json();
            })
            .then(users => {
                this.users = users;
            })
    }

});