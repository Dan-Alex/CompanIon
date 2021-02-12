<template>
    <div class="user" @click="click(user); admin ? admin = false : admin = true">

        <div class="user__fields">
            <div>{{user.surname}} {{user.name}}</div>
            <div>{{user.position}} {{user.departmentName}}</div>
            <div>e-mail: {{user.email}}</div>
            <div>тел.: {{user.phone}}</div>
        </div>
        <div v-if="admin === true && getRoles.includes('ROLE_ADMIN')" >
            <br/>
            <router-link class="btn" :to="{name : 'registration', params: {user: user}}">Изменить</router-link><br/><br/>
            <input class="btn" type="button" value="Удалить" @click="deleteUser(user)"/>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {

        props: {
            user:{
                type: Object
            }
        },

        data(){
          return {
              admin: false
          }
        },

        computed: {
          ...mapGetters(['getRoles'])
        },

        methods: {
            ...mapActions(['deleteUserAction']),

            deleteUser(user) {
                this.deleteUserAction(user);
            },

            click(user){
                this.$root.$emit('selectUser', user)
            }
        },
    }
</script>

<style scoped>

    .user {
        border: 6px solid #e1f5d0;
        border-radius: 5px;
        padding: 1em;
        cursor: pointer;
    }

    .user__fields {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-auto-columns: auto;
        text-align: center;
    }

    .btn {
        padding: 1em;
        font-size: 14pt;
        font-family: "Times New Roman";
        color: black;
        background-color: coral;
        cursor: pointer;
        width: min-content;
        height: min-content;
        text-decoration: none;
        border: none;
        border-radius: 5px;
    }

</style>