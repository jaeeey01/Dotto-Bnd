const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: process.env.REACT_APP_HTTP_HOST || 'http://3.39.107.150',
      changeOrigin: true,
    })
  )
}
