<template>
    <div id="user">
        {{user.name}} {{user.surname}} {{user.position}} {{user.departmentName}} <br/>
        e-mail: {{user.email}} тел.: {{user.phone}} <br/>
        <div v-if="getRoles.includes('ROLE_ADMIN')">
            <input type="button" value="Удалить" @click="deleteUser(user)"/>
            <router-link :to="{name : 'registration', params: {user: user}}">Изменить</router-link> <br/>
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

        computed: {
          ...mapGetters(['getRoles'])
        },

        methods: {
            ...mapActions(['deleteUserAction']),

            deleteUser(user) {
                this.deleteUserAction(user);
            }
        },
    }
</script>

<style scoped>
    #user {

        width: 400px;	height: 150px;
        background: palegreen; /* Цвет фона */
        outline: 2px solid #000; /* Чёрная рамка */
        border: 3px solid #fff; /* Белая рамка */
        border-radius: 10px; /* Радиус скругления */
    }
</style>