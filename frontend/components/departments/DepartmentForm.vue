<template>
    <div>
        <form action="/departments_list" method="post">

            <label for="name">Название:</label>
                <input type="text" name="name" v-model="department.name"><br/>
            <label >Начальник:</label> <input  v-if="department.chief !==''" type="button" value="Изменить" @click="department.chief = ''">
            <div >
                <find-user v-if="department.chief === ''"></find-user>
                <User v-if="department.chief !== ''" :user="department.chief"></User>
            </div>
            <input type="submit"
                   :value="isUpdate ? 'Изменить отдел' : 'Добавить'"
                   @click="isUpdate ? updateDepartment() : addDepartment()">
        </form>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    import User from '../users/User.vue';
    import FindUser from '../users/findUser.vue'

    export default {

        props: {
            departmentAttr: {
                type: Object
            }
        },

        data() {
            return {
                department : {
                    name: '',
                    chief: ''
                },
                isUpdate: false
            }
        },

        components: {
          User,
          FindUser
        },

        created(){
            if (this.departmentAttr) {
                this.department = this.departmentAttr;
                this.isUpdate = true;
            }

            this.$root.$on('selectUser', (selectedUser) => {
                this.department.chief = selectedUser;})
        },

        methods: {
            ...mapActions(['addDepartmentAction', 'updateDepartmentAction']),

            addDepartment() {
                this.addDepartmentAction(this.department)
            },

            updateDepartment() {
                this.updateDepartmentAction(this.department);
            }
        }
    }
</script>

<style scoped>

</style>