var personApi = Vue.resource('/person{/id}');

Vue.component('person-row', {
    props: ['person', 'editMethod'],
    template: '<div>' +
                '<table>' +
                    '<tr>' +
                        '<th>Id</th>' +
                        '<th>Name</th>' +
                        '<th>Last Name</th>' +
                        '<th>Birth Date</th>' +
                    '</tr>' +
                    '<td>' +
                        '<td>{{person.id}}</td>' +
                        '<td>{{person.name}}</td>' +
                        '<td>{{person.lastName}}</td>' +
                        '<td>{{person.birthDate}}</td>' +
                        '<td>' +
                            '<span>' +
                                '<input type="button" value="Edit" @click="edit">' +
                            '</span>' +
                        '</td>' +
                    '</tr>' +
                '</table>' +
              '</div>',
    methods: {
        edit: function () {
            this.editMethod(person);
        }
    }
});

Vue.component('person-list', {
        props: ['persons'],
        data: function() {
            return {
                person: null
            }
        },
        template: '<div>' +
            '<person-form :persons="persons" :pesonAttrs="person"/>' +
            '<person-row v-for="person in persons" :key="person.id" :person="person" :editMehot="editMethod">{{person.name}}</person-row>' +
            '</div>',
        created: function () {
            personApi.get().then(result =>
            result.json().then(data =>
            data.forEach(person => this.persons.push(person)))
        )
    },
        methods: {
            editMethod: function () {
                this.person = person
            }
        }
});

Vue.component('person-form', {
    props: ['persons', 'personAttr'],
    data: function() {
        return {
            id: '',
            name: '',
            lastName: '',
            birthDate: ''
        }
    },
    watch: {
        personAttr: function (newValue, oldValue) {
            this.id = newValue.id;
            this.name = newValue.name;
            this.lastName = newValue.lastName;
            this.birthDate = newValue.birthDate;
        }
    },
    template: '<div>' +
                    '<input type="text" placeholder="Write name" v-model="name">' +
                    '<input type="text" placeholder="Write lastname" v-model="lastName">' +
                    '<input type="text" placeholder="Write birth date" v-model="birthDate">' +
                    '<input type="button" value="Save" @click="save">' +
              '</div>',
    methods: {
        save: function () {
                    var person = {name: this.name, lastName: this.lastName, birthDate: this.birthDate};
                    if(this.id) {
                        personApi.update({id: this.id}, person);
                    } else {
                        personApi.save({}, person).then(result = >
                        result.json().then(data = > {
                            this.persons.push(data);
                        this.name = '';
                        this.lastName = '';
                        this.birthDate = '';
                    })
                    )
                    }
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<person-list :persons="persons"/>',
    data: {
        persons: []
    }
});