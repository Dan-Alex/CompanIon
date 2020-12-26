const usersUrl="http://localhost:9090/users/";
user = {
        username: '',
        name: '',
        surname: '',
        password: '',
        position: '',
        email: '',
        phone: '',
        departmentName: ''
};

new Vue({
   el:"#registration",

   data() {
       return { user }
   },

   methods: {
       registerUser() {
           fetch(usersUrl, {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify(this.user)
           });
       }
   }
});