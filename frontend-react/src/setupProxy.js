const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = (app) => {
  app.use(
    createProxyMiddleware('/api', {
      target: 'http://3.39.107.150',
      changeOrigin: true,
    })
  )
}
