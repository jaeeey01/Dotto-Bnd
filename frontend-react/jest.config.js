module.exports = {
  extensionsToTreatAsEsm: ['.ts'],
  testEnvironment: 'node',
  preset: 'ts-jest',
  //moduleFileExtensions: ['js', 'json', 'jsx', 'ts', 'tsx'],
  // moduleDirectories: ['src', 'node_modules'],
  moduleNameMapper: {
    '^@/(.*)': '<rootDir>/src/$1',
  },
  //testMatch: ['<rootDir>/**/*.test.(js|jsx|ts|tsx)'],
  // transform: {
  //   '^.+\\.(ts|tsx)$': 'ts-jest',
  // },
  // testRegex: '(/test/.*|(\\.|/)(test|spec))\\.(jsx?|tsx?)$',
  globals: {
    'ts-jest': {
      tsConfigFile: 'tsconfig.json',
      enableTsDiagnostics: true,
    },
  },
}
