<template>

    <v-card outlined>
        <v-card-title>
            ProductSearch # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="DeliveryStatus" v-model="item.deliveryStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ProductId" v-model="item.productId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderStatus" v-model="item.orderStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Stock" v-model="item.stock" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ProductSearchViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/productSearches/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

