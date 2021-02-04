<template>
    <div>
        <div v-if="isUpdate === false">
            <h3>{{company.name}}</h3>
            <ul>
                <li v-for="field in company.fields ">{{field.name}}: {{field.body}}</li>
            </ul>
        </div>

        <input v-if="getRoles.includes('ROLE_ADMIN') && isUpdate === false" type="button" value="Редактировать" @click="isUpdate = true">

        <div v-if="getRoles.includes('ROLE_ADMIN') && isUpdate === true">
            <label for="name">Название предприятия </label>
                <input name="name" type="text" v-model="company.name"><br/>
            <label for="fields">Поля:</label>
            <ul>
                <li><input type="button" value="Добавить" @click="company.fields.push({name: '', body: ''})"></li>
                <li v-for="field in company.fields "> <input name="name" type="text" v-model="field.name">
                                                      <input name="name" type="text" v-model="field.body"></li>

            </ul>

            <input type="submit" value="Сохранить изменения" @click="put()">
        </div>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'

    export default {
        data() {
            return{
                company: {
                    id: null,
                    name: '',
                    fields: []
                },
                isUpdate:false
            }
        },

        computed: mapGetters(['getCompany', 'getRoles']),

        methods: {
            ...mapActions(['getCompanyAction', 'putCompanyAction']),

            put(){
                this.putCompanyAction(this.company);
                this.isUpdate = false;
            }
        },



        created(){
            this.company = this.getCompany
            if (this.company.id === null) {
                delete this.company.id;
            }
        }
    }
</script>

<style scoped>

</style>