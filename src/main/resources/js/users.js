const usersUrl="http://localhost:9090/users/";

new Vue({

    el:'#usersList',

    data() {
        return {
            users: [],
            showDetails: false,
            showUpdate: false,
            selectedUser: ''
        }
    },

    methods:{
        showUser(user) {
            this.selectedUser = user;
            this.showDetails = true;
        },

        showUpdateUser(){
           this.showDetails=false;
           this.showUpdate=true;
        },

        updateUser() {
            fetch(usersUrl + this.selectedUser.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.selectedUser)
            });

            this.showUpdate = false;
            this.showDetails = false;

        }
    },

    created() {
        fetch(usersUrl)
            .then(response => {
                return response.json();
            })
            .then(users => {
                this.users = users;
            })
    }

});
